package com.ua_guys.service;

import com.ua_guys.dto.RouteDto;
import com.ua_guys.service.bvv.Coordinate;
import com.ua_guys.service.bvv.DepartureParameters;
import com.ua_guys.service.bvv.Stop;
import com.ua_guys.service.bvv.Trip;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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


    Trip[] trips = bvvApiService.departuresByStation(parameters);
    log.info("Was found {} departures for stop", trips.length);


    return null;
  }
}
