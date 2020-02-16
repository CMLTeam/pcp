package com.ua_guys.dto;

import com.ua_guys.service.bliq.Point;
import com.ua_guys.service.bliq.Type;
import com.ua_guys.service.bvv.Stop;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StationDto {

  private Long id;
  private Type type;
  private Point geometry;

  public static StationDto of(Stop stop) {
    StationDto stationDto = new StationDto();
    stationDto.setType(Type.Feature);
    stationDto.setId(stop.getId());
    stationDto.setGeometry(
        new Point(
            Type.Point,
            new float[] {stop.getLocation().getLongitude(), stop.getLocation().getLatitude()}));

    return stationDto;
  }
}
