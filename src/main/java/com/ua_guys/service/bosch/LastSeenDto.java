package com.ua_guys.service.bosch;

import lombok.Data;

@Data
public class LastSeenDto {

    private final BoschPropertiesDto properties;
    private final ParkingStateDto parkingState;
    private final PlayloadDto playload;
}
