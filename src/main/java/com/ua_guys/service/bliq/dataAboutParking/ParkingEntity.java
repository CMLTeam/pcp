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
  private OccupancyPredicted occupancyPredicted;
  private PriceInformation priceInformation;
}

class OccupancyPredicted {
  private Double parkingProbability;
}

class PriceInformation {

}