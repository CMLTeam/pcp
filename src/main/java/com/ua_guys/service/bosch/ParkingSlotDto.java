package com.ua_guys.service.bosch;

import lombok.Data;

@Data
public class ParkingSlotDto {

    private final String thingId;
    private final BoschFeaturesDto features;
}
