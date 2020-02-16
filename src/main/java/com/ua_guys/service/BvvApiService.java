package com.ua_guys.service;

import com.ua_guys.service.bvv.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class BvvApiService {

  private static final String URL = "https://3.vbb.transport.rest/";

  private final RestTemplate restTemplate;

  public Stop[] stationNearByCoordinate(Coordinate coordinate, Integer distance) {
    String suffix = "stops/nearby";

    String url = URL + suffix;

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("latitude", coordinate.getLatitude())
            .queryParam("longitude", coordinate.getLongitude())
            .queryParam("distance", distance);

    String uriString = builder.toUriString();
    ResponseEntity<Stop[]> response = restTemplate.getForEntity(uriString, Stop[].class);

    return response.getBody();
  }

  public Departure[] departuresByStation(DepartureParameters parameters) {
    String suffix = "stops/";

    String url = URL + suffix + parameters.getStopId() + "/departures";

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("when", parameters.getWhen())
            .queryParam("duration", parameters.getDuration());

    String uriString = builder.toUriString();
    log.info("Get data from url={}", uriString);

    ResponseEntity<Departure[]> response =
        restTemplate.getForEntity(builder.build().toUri(), Departure[].class);

    return response.getBody();
  }

  public Trip trip(String tripId, String lineName) {
    String suffix = "trips/";

    String url = URL + suffix + tripId;

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url).queryParam("lineName", lineName);

    String uriString = builder.toUriString();
    log.info("Get data from url={}", uriString);

    ResponseEntity<Trip> response =
        restTemplate.getForEntity(builder.build().toUri(), Trip.class);

    return response.getBody();
  }
}
