package com.ua_guys.service;

import com.ua_guys.service.bvv.Coordinate;
import com.ua_guys.service.bvv.Stop;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PublicTransportService {

  public static final int METERS_PER_MINUTE = 50;
  private final String WHEN = "today 7pm";

  private final BvvApiService bvvApiService;

  public Object calculateRoutes(Coordinate coordinate, Integer duration) {
    int maxDistanceToStation = duration * METERS_PER_MINUTE;
    List<Stop> stops =
        Arrays.asList(bvvApiService.stationNearByCoordinate(coordinate, maxDistanceToStation));


    return null;
  }
}
