package com.ua_guys.service.bosch;

import lombok.Data;

import java.util.List;

@Data
public class ParkingPlaceParameters {
    private List<ParkingSlotDto> items;
}

