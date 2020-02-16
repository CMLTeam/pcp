package com.ua_guys.service.bvv;

import lombok.Data;

@Data
public class Stop {

  private String stop;
  private Long id;
  private String name;
  private Location location;
  private Integer distance;
}
