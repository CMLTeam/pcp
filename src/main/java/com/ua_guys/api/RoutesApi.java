package com.ua_guys.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RoutesApi {


  // TODO for reques routes from mobile app
  @GetMapping
  public Object getRoutes(@RequestParam Integer duration){

    return null;
  }
}
