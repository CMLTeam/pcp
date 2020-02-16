package com.ua_guys.service.bvv;

import lombok.Data;

import java.util.List;

@Data
public class Trip {

  private Stop origin;
  private Stop destination;
  private String departure;
  private String arrival;
  private Line line;
  private String direction;
  private List<Stopover> stopovers;
}
