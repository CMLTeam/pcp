package com.ua_guys.api;

import com.ua_guys.service.BliqApiService;
import com.ua_guys.service.BoschApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test-bosch")
@RequiredArgsConstructor
@Slf4j
public class TestBoschAPI {

    private final BoschApiService boschApiService;

    @GetMapping
    public Object getTest() {
        log.info("Request bosch started");
        return boschApiService.getDataFromParkingSensors();
    }
}
