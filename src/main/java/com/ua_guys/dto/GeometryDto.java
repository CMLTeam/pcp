package com.ua_guys.dto;

import com.ua_guys.service.bliq.Point;
import com.ua_guys.service.bliq.Type;
import lombok.Data;

@Data
public class GeometryDto {

    private final Type type;
    private final Point point;
}
