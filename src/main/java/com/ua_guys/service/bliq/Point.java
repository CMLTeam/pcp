package com.ua_guys.service.bliq;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {
  private final Type type;
  private final float[] coordinates;

  public Point(Type type, float longitude, float latitude) {
    this.type = type;
    this.coordinates = new float[] {longitude, latitude};
  }
}
