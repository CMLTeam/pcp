package com.ua_guys.service.bvv;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Stop {

  private String type;
  private Long id;
  private String name;
  private Location location;
  private Integer distance;
}
