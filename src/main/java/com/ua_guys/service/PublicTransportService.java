package com.ua_guys.service;

import com.ua_guys.service.bvv.Coordinate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class PublicTransportService {

  private final BvvApiService bvvApiService;

  public Object calculateRoutes(Coordinate coordinate, LocalDate when, Integer duration){
    return null;
  }


}
