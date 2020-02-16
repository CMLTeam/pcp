package com.ua_guys.service.bliq.dataAboutParking;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonSerialize
@Getter
@Setter
@ToString
public class ParkingEntity {
  private Integer capacity;
  private CenterPoint centerPoint;
  private Occupancy occupancy;
  private PriceInformation priceInformation;
}
