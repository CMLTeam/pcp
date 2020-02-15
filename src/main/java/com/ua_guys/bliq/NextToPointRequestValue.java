package com.ua_guys.bliq;

import lombok.Data;

@Data
public class NextToPointRequestValue {
  private final Point point;
  private final int limit;
}
