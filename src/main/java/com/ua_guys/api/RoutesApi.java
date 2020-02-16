package com.ua_guys.api;

import com.ua_guys.service.PublicTransportService;
import com.ua_guys.service.bvv.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RoutesApi {

  private final PublicTransportService transportService;

  @GetMapping
  public Object getRoutes(
      @RequestParam Float longitude, @RequestParam Float latitude, @RequestParam Integer duration) {

    return transportService.calculateRoutes(new Coordinate(longitude, latitude), duration);
  }
}
