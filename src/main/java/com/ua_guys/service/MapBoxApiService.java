package com.ua_guys.service;

import com.ua_guys.service.bvv.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapBoxApiService {
  private static final String URL = "https://api.mapbox.com/";
  private static final String ACCESS_TOKEN =
      "pk.eyJ1Ijoicm9tYWthdHNhIiwiYSI6ImNqbnJqd3FkZjA2Mmczb2xrMHliZmgxeTIifQ.AwLH4y3Et0uW2IeOgIAJDA";

  private final RestTemplate restTemplate;

  public Object getCenterAndRadius(Coordinate coordinate, int minutes) {
    String suffix =
        "isochrone/v1/mapbox/driving/" + coordinate.getLongitude() + "," + coordinate.getLatitude();

    String url = URL + suffix;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

    HttpEntity entity = new HttpEntity(headers);

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("contours_minutes", minutes)
            .queryParam("polygons", true)
            .queryParam("access_token", ACCESS_TOKEN);

    Map<String, Object> params = new HashMap<>();
    params.put("contours_minutes", minutes);
    params.put("polygons", true);
    params.put("access_token", ACCESS_TOKEN);

    String uriString = builder.toUriString();
    ResponseEntity<Object> response =
        restTemplate.exchange(uriString, HttpMethod.GET, entity, Object.class);

    return response;
  }
}
