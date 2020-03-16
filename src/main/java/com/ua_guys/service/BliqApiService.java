package com.ua_guys.service;

import com.ua_guys.service.bliq.*;
import com.ua_guys.service.bliq.dataAboutParking.ParkingDataFromApi;
import com.ua_guys.service.bvv.Coordinate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BliqApiService {

  public static final String API_KEY = "apikey";
  private static final String BLIQ_API_URL = "https://api.aipark.io:443/aipark/v2/";
  private static final String API_KEY_VALUE = "JHbZ1VOvla33Vv3Df4Vg8jtTb5CgN6OC";
  private final RestTemplate restTemplate;

  public ParkingDataFromApi getDataAboutParking(Coordinate coordinate) {
    String url = BLIQ_API_URL + "getOnStreetParkingOptions";

    HttpHeaders headers = getHttpHeaders();

    AskParkAssistantRequestBody body = new AskParkAssistantRequestBody();
    body.setMapLayers(new MapLayer[] {MapLayer.RULES, MapLayer.PREDICTION});
    body.setOutputFormat(OutputFormat.GEO_JSON);
    body.setMapResolutionLevel(MapResolutionLevel.BLOCK);

    RequestType requestType = new RequestType();
    requestType.setDataType(DataType.NextToPointRequest);
    Map<String, Object> timeStamp = new HashMap<>();
    timeStamp.put("dateString", "2020-03-17T18:00:00+02:00");
    //    timeStamp.put("unixMilliSeconds", 1581774754639L);
    requestType.setTimestampDescription(timeStamp);

    //    Point point = new Point(Type.Point, new float[]{13.3935111757F, 52.5159870398F});
    Point point = new Point(Type.Point, coordinate.getLongitude(), coordinate.getLatitude());
    NextToPointRequestValue value = new NextToPointRequestValue(point, 5);
    requestType.setValue(value);

    body.setRequestType(requestType);
    HttpEntity<AskParkAssistantRequestBody> request = new HttpEntity<>(body, headers);
    log.info("request: {}", request.toString());
    ResponseEntity<ParkingDataFromApi> responseEntity;
    responseEntity = restTemplate.postForEntity(url, request, ParkingDataFromApi.class);
    return responseEntity.getBody();
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(API_KEY, API_KEY_VALUE);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

}
