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
public class GeometryDTO {
  private String type;
  private List<float[]> coordinates;
}
