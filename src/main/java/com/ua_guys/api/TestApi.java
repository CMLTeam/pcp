package com.ua_guys.api;

import com.ua_guys.service.BliqApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
@Slf4j
public class TestApi {

  private final BliqApiService bliqApiService;


  @GetMapping
  public Object getTest() {
    log.info("Request started");
    return bliqApiService.getDataAboutParking();
  }
}
