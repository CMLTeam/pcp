package com.ua_guys.service;

import com.ua_guys.dto.RouteDto;
import com.ua_guys.service.bvv.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

          routes.addAll(extractRoutesInTwoDirections(stop, trip, leftTime));
        });

    return routes;
  }

  private List<RouteDto> extractRoutesInTwoDirections(Stop stop, Trip trip, Integer duration) {
    List<Stop> allTripStops =
        trip.getStopovers().stream().map(Stopover::getStop).collect(Collectors.toList());
    int stopIndex = allTripStops.indexOf(stop);
    int stopCount = 0;
    List<Stop> rightStops = new ArrayList<>();
    for (int i = stopIndex;
        i < allTripStops.size() && stopCount * MINUTES_BETWEEN_STOPS < duration;
        i++, stopCount++) {
      rightStops.add(allTripStops.get(i));
    }
    List<Stop> leftStops = new ArrayList<>();
    for (int i = stopIndex;
        i >= 0 && stopCount * MINUTES_BETWEEN_STOPS < duration;
        i--, stopCount++) {
      leftStops.add(allTripStops.get(i));
    }

//    RouteDto rout = new RouteDto();

    return Collections.EMPTY_LIST;
  }
}
