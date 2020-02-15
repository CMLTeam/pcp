package com.ua_guys.service;

import com.ua_guys.service.bliq.*;
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

  private final static String BLIQ_API_URL = "https://api.aipark.io:443/aipark/v2/";
  private final static String API_KEY_VALUE = "JHbZ1VOvla33Vv3Df4Vg8jtTb5CgN6OC";
  public static final String API_KEY = "apikey";

  private final RestTemplate restTemplate;

  public Object getDataAboutParking() {
    String url = BLIQ_API_URL + "getOnStreetParkingOptions";

    HttpHeaders headers = getHttpHeaders();

    AskParkAssistantBody body = new AskParkAssistantBody();
    body.setMapLayers(new MapLayer[]{MapLayer.RULES, MapLayer.PREDICTION});
    body.setOutputFormat(OutputFormat.GEO_JSON);
    body.setMapResolutionLevel(MapResolutionLevel.BLOCK);

    RequestType requestType = new RequestType();
    requestType.setDataType(DataType.NextToPointRequest);
    Map<String, Object> timeStamp = new
      HashMap<>();
    timeStamp.put("unixMilliSeconds", 1581774754639L);
    requestType.setTimestampDescription(timeStamp);

    Point point = new Point(Type.Point, new float[]{13.3935111757F, 52.5159870398F});
    NextToPointRequestValue value = new NextToPointRequestValue(point, 20);
    requestType.setValue(value);

    body.setRequestType(requestType);
    HttpEntity<AskParkAssistantBody> request = new HttpEntity<>(body, headers);
    ResponseEntity<Object> responseEntity;
    try {
      responseEntity = restTemplate.postForEntity(url, request, Object.class);
    } catch (Exception ex) {
      log.error("Something goes wrong", ex);
      responseEntity = null;
    }
    return responseEntity;
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(API_KEY, API_KEY_VALUE);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

}
