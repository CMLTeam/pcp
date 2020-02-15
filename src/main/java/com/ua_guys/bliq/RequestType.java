package com.ua_guys.bliq;

import lombok.Data;

import java.util.Map;

@Data
public class RequestType {
  private DataType dataType;
  private Map<String, Object> timestampDescription;
  private Object value;
}
