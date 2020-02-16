package com.ua_guys.service.bliq.dataAboutParking;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

@Data
class Occupancy {
  private OccupancyPredicted occupancyPredicted;
}

@Data
class CenterPoint {
  private Float[] coordinates;
}

@Data
class OccupancyPredicted {
  private Double parkingProbability;
}

@Data
class PriceInformation {
  private List<Schedule> schedules;
}

@Data
class Schedule {
  private PriceModel priceModel;
}

@Data
class PriceModel {
  private List<Price> prices;
}

@Data
class Price {
  private String currencyCode;
  private String priceValue;
}
