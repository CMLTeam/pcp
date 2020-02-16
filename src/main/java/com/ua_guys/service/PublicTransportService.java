package com.ua_guys.service;

import com.ua_guys.dto.RouteDto;
import com.ua_guys.dto.StationDto;
import com.ua_guys.dto.TripDto;
import com.ua_guys.service.bvv.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PublicTransportService {

  public static final int METERS_PER_MINUTE = 60;
  public static final int MINUTES_BETWEEN_STOPS = 2;
  private static final String WHEN = "today 7pm";

  private final BvvApiService bvvApiService;

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
    int stopIndex = allTripStops.indexOf(stop);
    int rightStopCount = 0;
    List<Stop> rightStops = new ArrayList<>();
    for (int i = stopIndex;
        i < allTripStops.size() && rightStopCount * MINUTES_BETWEEN_STOPS < duration;
        i++, rightStopCount++) {
      rightStops.add(allTripStops.get(i));
    }
    RouteDto rightRoute =
        new RouteDto(
            StationDto.of(CollectionUtils.lastElement(rightStops)),
            TripDto.of(trip, rightStops, rightStopCount * MINUTES_BETWEEN_STOPS));

    List<Stop> leftStops = new ArrayList<>();

    int leftStopCount = 0;

    for (int i = stopIndex;
        i >= 0 && leftStopCount * MINUTES_BETWEEN_STOPS < duration;
        i--, leftStopCount++) {
      leftStops.add(allTripStops.get(i));
    }

    RouteDto leftRoute =
        new RouteDto(
            StationDto.of(CollectionUtils.lastElement(leftStops)),
            TripDto.of(trip, leftStops, leftStopCount * MINUTES_BETWEEN_STOPS));

    return Arrays.asList(rightRoute, leftRoute);
  }
}
