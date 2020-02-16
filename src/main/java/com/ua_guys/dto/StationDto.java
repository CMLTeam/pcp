package com.ua_guys.dto;

import com.ua_guys.service.bliq.Type;
import lombok.Data;

@Data
public class StationDto {

    private final String id;
    private final Type type;
    private final GeometryDto geometryDto;

}
