package com.ua_guys.service.bosch;

import lombok.Data;

@Data
public class BoschFeaturesDto {

    private LastSeenDto lastSeen;
    private ParkingStateDto parkingState;
    private PayloadDto payload;

}
