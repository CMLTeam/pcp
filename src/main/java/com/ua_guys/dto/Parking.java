package com.ua_guys.dto;

import com.ua_guys.service.bliq.Point;
import com.ua_guys.service.bliq.Type;
import com.ua_guys.service.bliq.dataAboutParking.ParkingFeture;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Random;

@Data
@Builder
public class Parking {
  private Integer capacity;
  private Integer probability;
  private Integer available;
  private BigDecimal price;
  private Type type;
  private Point geometry;

  public static Parking of(ParkingFeture parkingFeture) {
    Parking parking =
        Parking.builder()
            .capacity(parkingFeture.getProperties().getParkingEntity().getCapacity())
            .probability(
                parkingFeture
                    .getProperties()
                    .getParkingEntity()
                    .getOccupancy()
                    .getOccupancyPredicted()
                    .getParkingProbability())
            .available(
                parkingFeture
                        .getProperties()
                        .getParkingEntity()
                        .getOccupancy()
                        .getOccupancyPredicted()
                        .getParkingProbability()
                    * parkingFeture.getProperties().getParkingEntity().getCapacity()
                    / 100)
            .price(BigDecimal.valueOf(new Random().nextFloat() * 2))
            .type(Type.Feature)
            .geometry(new Point(Type.Point, parkingFeture.getGeometry().getCoordinates().get(0)))
            .build();

    return parking;
  }
}
