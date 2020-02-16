package com.ua_guys.service.bliq.dataAboutParking;

import lombok.Data;

import java.util.List;

@Data
public class PriceInformation {
  private List<Schedule> schedules;
}
