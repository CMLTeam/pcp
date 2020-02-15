package com.ua_guys.service;

import com.ua_guys.service.bvv.Coordinate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    HttpEntity entity = new HttpEntity(headers);

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("latitude", coordinate.getLatitude())
            .queryParam("longitude", coordinate.getLongitude())
            .queryParam("distance", distance);

    Map<String, Object> params = new HashMap<>();
    params.put("latitude", coordinate.getLatitude());
    params.put("longitude", coordinate.getLongitude());
    params.put("distance", distance);

    String uriString = builder.toUriString();
    ResponseEntity<Object> response =
        restTemplate.exchange(uriString, HttpMethod.GET, entity, Object.class);

    return response;
  }

  public Object departuresByStation(Coordinate coordinate, Integer distance) {
    String suffix = "stops/nearby";

    String url = URL + suffix;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity entity = new HttpEntity(headers);

    UriComponentsBuilder builder =
      UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("latitude", coordinate.getLatitude())
        .queryParam("longitude", coordinate.getLongitude())
        .queryParam("distance", distance);

    Map<String, Object> params = new HashMap<>();
    params.put("latitude", coordinate.getLatitude());
    params.put("longitude", coordinate.getLongitude());
    params.put("distance", distance);

    String uriString = builder.toUriString();
    ResponseEntity<Object> response =
      restTemplate.exchange(uriString, HttpMethod.GET, entity, Object.class);

    return response;
  }


}
