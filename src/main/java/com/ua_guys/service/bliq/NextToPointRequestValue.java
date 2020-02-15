package com.ua_guys.service.bliq;

import lombok.Data;

@Data
public class NextToPointRequestValue {
  private final Point point;
  private final int limit;
}
