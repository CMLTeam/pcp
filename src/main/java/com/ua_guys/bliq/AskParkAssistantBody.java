package com.ua_guys.bliq;

import lombok.Data;

@Data
public class AskParkAssistantBody {
  private MapLayer[] mapLayers;
  private OutputFormat outputFormat;
  private MapResolutionLevel mapResolutionLevel;
  private RequestType requestType;
}
