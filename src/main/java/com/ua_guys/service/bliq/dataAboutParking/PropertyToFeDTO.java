package com.ua_guys.service.bliq.dataAboutParking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyToFeDTO {
  private String capacity;
  private Double probability;
  private Integer available;
  private Double price;
}
