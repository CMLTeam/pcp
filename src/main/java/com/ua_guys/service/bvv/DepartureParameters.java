package com.ua_guys.service.bvv;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepartureParameters {
  private Long stopId;
  private String when;
  private Integer duration;
}
