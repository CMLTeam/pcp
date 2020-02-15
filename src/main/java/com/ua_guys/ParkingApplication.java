package com.ua_guys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
public class ParkingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ParkingApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
