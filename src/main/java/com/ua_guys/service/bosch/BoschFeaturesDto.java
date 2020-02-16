package com.ua_guys.service.bosch;

import lombok.Data;

@Data
public class BoschFeaturesDto {

    private final LastSeenDto lastSeen;
    private final ParkingStateDto parkingState;
    private final PlayloadDto playload;

}
