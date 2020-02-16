package com.ua_guys.dto;

import lombok.Data;

@Data
public class TripDto {

    private final String departureTime;
    private final String line;
    private final Number travelTime;
    private final StopOvers stopovers;

}
