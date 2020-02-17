package com.ua_guys.dto;

import com.ua_guys.service.bliq.Point;
import com.ua_guys.service.bliq.Type;
import com.ua_guys.service.bliq.dataAboutParking.ParkingFeture;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Data
@Builder
public class Parking {
  private Type type;
  private Point geometry;
  private Map<String, Object> properties;

  public static Parking of(ParkingFeture parkingFeture) {
    Map<String, Object> properties = new HashMap<>();
    properties.put("capacity", parkingFeture.getProperties().getParkingEntity().getCapacity());
    properties.put(
        "probability",
        parkingFeture
            .getProperties()
            .getParkingEntity()
            .getOccupancy()
            .getOccupancyPredicted()
            .getParkingProbability());
    properties.put(
        "available",
        parkingFeture
                .getProperties()
                .getParkingEntity()
                .getOccupancy()
                .getOccupancyPredicted()
                .getParkingProbability()
            * parkingFeture.getProperties().getParkingEntity().getCapacity()
            / 100);
    properties.put("price", BigDecimal.valueOf(new Random().nextFloat() * 2));

    Parking parking =
        Parking.builder()
            .type(Type.Feature)
            .properties(properties)
            .geometry(new Point(Type.Point, parkingFeture.getGeometry().getCoordinates().get(0)))
            .build();

    return parking;
  }
}
