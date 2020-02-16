package com.ua_guys.service;

import com.ua_guys.dto.*;
import com.ua_guys.service.bliq.Type;
import com.ua_guys.service.bliq.dataAboutParking.ParkingDataFromApi;
import com.ua_guys.service.bvv.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PublicTransportService {

  public static final int METERS_PER_MINUTE = 60;
  public static final int MINUTES_BETWEEN_STOPS = 2;
  private static final String WHEN = "today 7pm";

  private final BvvApiService bvvApiService;
  private final BliqApiService bliqApiService;

  public List<RouteDto> calculateRoutes(Coordinate coordinate, Integer duration) {
    int maxDistanceToStation = duration * METERS_PER_MINUTE;
    log.info(
        "Start calculate routes for location={} and duration={} (about {} meters)",
        coordinate,
        duration,
        maxDistanceToStation);
    Stop stop =
        Arrays.stream(bvvApiService.stationNearByCoordinate(coordinate, maxDistanceToStation))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No stops found"));
    log.info("Was found stop {}", stop);

    DepartureParameters parameters =
        DepartureParameters.builder().when(WHEN).duration(duration).stopId(stop.getId()).build();

    Integer leftTime = duration - stop.getDistance() / METERS_PER_MINUTE;

    List<Departure> departures = bvvApiService.departuresByStation(parameters);
    log.info("Was found {} departures for stop", departures.size());

    List<RouteDto> routes = new ArrayList<>(departures.size() * 2);

    departures.forEach(
        dprt -> {
          Trip trip = bvvApiService.trip(dprt.getTripId(), dprt.getLine().getName());
          log.info("Was found trip {} for departureId={}", trip.getId(), dprt.getTripId());

          routes.addAll(extractRoutesInTwoDirections(stop, trip, leftTime));
        });
    log.info("Was calculated {} routes", routes.size());
    return routes;
  }

  private List<RouteDto> extractRoutesInTwoDirections(Stop stop, Trip trip, Integer duration) {
    List<Stop> allTripStops =
        trip.getStopovers().stream().map(Stopover::getStop).collect(Collectors.toList());
    if (!allTripStops.contains(stop)) {
      return Collections.emptyList();
    }
    final int stopIndex = allTripStops.indexOf(stop);
    int rightStopCount = 0;
    List<Stop> rightStops = new ArrayList<>();
    for (int i = stopIndex;
        i < allTripStops.size() && rightStopCount * MINUTES_BETWEEN_STOPS < duration;
        i++, rightStopCount++) {
      log.info("Index of right {} whole size {}", i, allTripStops.size());
      rightStops.add(allTripStops.get(i));
    }

    Stop lastRightStop = CollectionUtils.lastElement(rightStops);
    ParkingDataFromApi rightParkingDataFromApi =
        bliqApiService.getDataAboutParking(
            new Coordinate(
                lastRightStop.getLocation().getLongitude(),
                lastRightStop.getLocation().getLatitude()));
    log.info("For right stop was got {} parkings", rightParkingDataFromApi.getFeatures().length);

    List<Parking> rightParkings =
        Arrays.stream(rightParkingDataFromApi.getFeatures())
            .map(Parking::of)
            .collect(Collectors.toList());
    ParkingData rightStopParkingData = new ParkingData(Type.FeatureCollection, rightParkings);
    RouteDto rightRoute =
        new RouteDto(
            StationDto.of(lastRightStop),
            TripDto.of(trip, rightStops, rightStopCount * MINUTES_BETWEEN_STOPS),
            rightStopParkingData);

    List<Stop> leftStops = new ArrayList<>();

    int leftStopCount = 0;

    for (int i = stopIndex;
        i >= 0 && leftStopCount * MINUTES_BETWEEN_STOPS < duration;
        i--, leftStopCount++) {
      log.info("Index of left {} whole size {}", leftStopCount, allTripStops.size());
      leftStops.add(allTripStops.get(i));
    }

    Stop lastLeftStop = CollectionUtils.lastElement(leftStops);
    ParkingDataFromApi leftParkingDataFromApi =
        bliqApiService.getDataAboutParking(
            new Coordinate(
                lastLeftStop.getLocation().getLongitude(),
                lastLeftStop.getLocation().getLatitude()));
    log.info("For left stop was got {} parkings", leftParkingDataFromApi.getFeatures().length);
    List<Parking> leftParkings =
        Arrays.stream(leftParkingDataFromApi.getFeatures())
            .map(Parking::of)
            .collect(Collectors.toList());
    ParkingData leftStopParkingData = new ParkingData(Type.FeatureCollection, leftParkings);
    RouteDto leftRoute =
        new RouteDto(
            StationDto.of(lastLeftStop),
            TripDto.of(trip, leftStops, leftStopCount * MINUTES_BETWEEN_STOPS),
            leftStopParkingData);

    return Arrays.asList(rightRoute, leftRoute);
  }
}
