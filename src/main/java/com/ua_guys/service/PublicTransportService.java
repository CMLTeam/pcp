package com.ua_guys.service;

import com.ua_guys.dto.RouteDto;
import com.ua_guys.service.bvv.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PublicTransportService {

  public static final int METERS_PER_MINUTE = 60;
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

    Integer leftTime = stop.getDistance() / METERS_PER_MINUTE;

    Departure[] departures = bvvApiService.departuresByStation(parameters);
    log.info("Was found {} departures for stop", departures.length);

    List<Trip> trips =
        Arrays.stream(departures)
            .map(dprt -> {
              Trip trip = bvvApiService.trip(dprt.getTripId(), dprt.getLine().getName());
              if(trip.getStopovers().stream().noneMatch(st -> st.getStop().getId().equals(stop.getId()))){
                throw new IllegalArgumentException("Stop not equal origin stop id");
              }
              return trip;
            })
            .collect(Collectors.toList());


    return null;
  }
}
