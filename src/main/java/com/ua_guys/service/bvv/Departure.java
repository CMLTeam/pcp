package com.ua_guys.service.bvv;

import lombok.Data;

@Data
public class Departure {

  private String tripId;
  private Stop stop;
  private String when;
  private String direction;
  private Line line;
}
