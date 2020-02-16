package com.ua_guys.dto;

import com.ua_guys.service.bliq.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParkingData {
  private Type type;
  private List<Parking> parkings;
}
