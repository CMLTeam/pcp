package com.ua_guys.dto;

import lombok.Data;

@Data
public class RouteDto {

    private final StationDto firstStop;
    private final TripDto tripDto;
}
