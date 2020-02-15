package com.ua_guys.service.bliq;

import lombok.Data;

@Data
public class AskParkAssistantRequestBody {
  private MapLayer[] mapLayers;
  private OutputFormat outputFormat;
  private MapResolutionLevel mapResolutionLevel;
  private RequestType requestType;
}
