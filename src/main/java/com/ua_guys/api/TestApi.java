package com.ua_guys.api;

import com.ua_guys.service.BliqApiService;
import com.ua_guys.service.BvvApiService;
import com.ua_guys.service.bvv.Coordinate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
@Slf4j
public class TestApi {

  private final BliqApiService bliqApiService;
  private final BvvApiService bvvApiService;

  @GetMapping("/test")
  public Object getTest() {
    log.info("Request started");
    return bliqApiService.getDataAboutParking();
  }

  @GetMapping("/stopsNearBy")
  public Object stopsNearBy(
      @RequestParam Float longitude,
      @RequestParam Float latitude,
      @RequestParam(required = false) Integer distance) {

    log.info("Station nearby longitude={} latitude={} distance={}", longitude, latitude, distance);
    return bvvApiService.stationNearByCoordinate(new Coordinate(longitude, latitude), distance);
  }
}
