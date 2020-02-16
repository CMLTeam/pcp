package com.ua_guys.dto;

import com.ua_guys.service.bliq.Type;
import com.ua_guys.service.bvv.Stop;
import com.ua_guys.service.bvv.Stopover;
import com.ua_guys.service.bvv.Trip;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TripDto {

  private final String departureTime;
  private final String line;
  private final Integer travelTime;
  private final StopOver stopovers;

  public static TripDto of(Trip trip, List<Stop> rightStops, Integer travelTime) {
    TripDto tripDto =
        TripDto.builder()
            .departureTime(trip.getDeparture())
            .line(trip.getLine().getName())
            .travelTime(travelTime)
            .stopovers(
                new StopOver(
                    Type.FeatureCollection,
                    rightStops.stream().map(StationDto::of).collect(Collectors.toList())))
            .build();

    return tripDto;
  }
}
