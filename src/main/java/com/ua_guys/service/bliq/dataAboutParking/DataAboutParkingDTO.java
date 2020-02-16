package com.ua_guys.service.bliq.dataAboutParking;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonSerialize
@Getter
@Setter
@ToString
public class DataAboutParkingDTO {
  private String type;
  private String dataType;
  private DataAboutParkingFeatureDTO[] features;

}
