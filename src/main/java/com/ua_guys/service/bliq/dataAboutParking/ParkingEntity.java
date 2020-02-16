package com.ua_guys.service.bliq.dataAboutParking;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
  private Occupancy occupancy;
  private PriceInformation priceInformation;
}


@JsonSerialize
@Getter
@Setter
@ToString
class Occupancy {
  private OccupancyPredicted occupancyPredicted;
}


@JsonSerialize
@Getter
@Setter
@ToString
class OccupancyPredicted {
  private Double parkingProbability;
}


@JsonSerialize
@Getter
@Setter
@ToString
class PriceInformation {
  private List<Schedule> schedules;
}


@JsonSerialize
@Getter
@Setter
@ToString
class Schedule {
  private PriceModel priceModel;
}


@JsonSerialize
@Getter
@Setter
@ToString
class PriceModel {
  private List<Price> prices;
}


@JsonSerialize
@Getter
@Setter
@ToString
class Price {
  private String currencyCode;
  private String priceValue;
}