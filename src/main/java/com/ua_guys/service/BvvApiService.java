package com.ua_guys.service;

import com.ua_guys.service.bvv.Coordinate;
import com.ua_guys.service.bvv.DepartureParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BvvApiService {

  private static final String URL = "https://3.vbb.transport.rest/";

  private final RestTemplate restTemplate;

  public Object stationNearByCoordinate(Coordinate coordinate, Integer distance) {
    String suffix = "stops/nearby";

    String url = URL + suffix;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("latitude", coordinate.getLatitude())
            .queryParam("longitude", coordinate.getLongitude())
            .queryParam("distance", distance);

    String uriString = builder.toUriString();
    ResponseEntity<Object> response =
        restTemplate.getForEntity(uriString, Object.class);

    return response;
  }

  public Object departuresByStation(DepartureParameters parameters) {
    String suffix = "stops/";

    String url = URL + suffix + parameters.getStationId() + "/departures";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("when", parameters.getWhen())
            .queryParam("duration", parameters.getDuration());

    String uriString = builder.toUriString();
    log.info("Get data from url={}", uriString);

    ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

    return response;
  }
}
