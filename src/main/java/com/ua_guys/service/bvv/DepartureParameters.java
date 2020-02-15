package com.ua_guys.service.bvv;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepartureParameters {
  private Long stationId;
  private String when;
  private Integer duration;
}
