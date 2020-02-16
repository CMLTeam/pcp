package com.ua_guys.dto;

import com.ua_guys.service.bliq.Type;
import lombok.Data;

import java.util.List;

@Data
public class StopOvers {

    private final Type type;
    private final List<StationDto> features;

}
