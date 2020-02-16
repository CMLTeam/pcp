package com.ua_guys.service.bliq.dataAboutParking;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@JsonSerialize
@Getter
@Setter
@ToString
public class ParkingFeture {
  private String type;
  private BigDecimal id;
  private GeometryDTO geometry;
  private PropertyDTO properties;
}
