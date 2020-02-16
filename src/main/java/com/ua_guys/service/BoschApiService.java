package com.ua_guys.service;

import com.ua_guys.service.bliq.AskParkAssistantRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoschApiService {

    private final static String BOSCH_API_URL = "https://things.eu-1.bosch-iot-suite.com/api/2/search/things?namespaces=io.bosch.bcx2020";
    private final static String API_KEY_VALUE = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJwRlhzTXhHaG5YSmd6ZzlhTzl4WVVUWWVnQ1A0WHNudUdoUUVlUWFBUXJJIn0.eyJqdGkiOiJlOGM0YzRlZC01ZjViLTQ4YmItODVkYy1mZjVlZGUyMTljN2IiLCJleHAiOjE1ODE4MDcwMzgsIm5iZiI6MCwiaWF0IjoxNTgxODAzNDM4LCJpc3MiOiJodHRwczovL2FjY2Vzcy5ib3NjaC1pb3Qtc3VpdGUuY29tL2F1dGgvcmVhbG1zL2lvdC1zdWl0ZSIsImF1ZCI6WyJzZXJ2aWNlOmlvdC10aGluZ3MtZXUtMTphODYwZDRjMS0yMzUxLTQ5NjUtODJkMC1lYmMzNzdhYTE5YWFfdGhpbmdzIiwic2VydmljZTppb3QtaHViLXByb2Q6dGE4NjBkNGMxMjM1MTQ5NjU4MmQwZWJjMzc3YWExOWFhX2h1YiIsImFjY291bnQiXSwic3ViIjoiY2M5YzBlN2UtNWNjZS00MzVkLWFkMjQtOWY0MDlkZGYyMTgzIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiOTY5ZThjYzctMjc3ZS00MWEyLWE2YmItN2E4MTVkY2UzMTY4IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZTIzMjcxZTYtMzE0My00NTc2LTljM2QtMGFkYTNmNzhhZmE0IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsic2VydmljZTppb3QtdGhpbmdzLWV1LTE6YTg2MGQ0YzEtMjM1MS00OTY1LTgyZDAtZWJjMzc3YWExOWFhX3RoaW5ncyI6eyJyb2xlcyI6WyJmdWxsLWFjY2VzcyJdfSwic2VydmljZTppb3QtaHViLXByb2Q6dGE4NjBkNGMxMjM1MTQ5NjU4MmQwZWJjMzc3YWExOWFhX2h1YiI6eyJyb2xlcyI6WyJmdWxsLWFjY2VzcyJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiY2xpZW50SG9zdCI6IjMuMTIzLjg4LjE5OCIsImNsaWVudElkIjoiOTY5ZThjYzctMjc3ZS00MWEyLWE2YmItN2E4MTVkY2UzMTY4IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtOTY5ZThjYzctMjc3ZS00MWEyLWE2YmItN2E4MTVkY2UzMTY4IiwiY2xpZW50QWRkcmVzcyI6IjMuMTIzLjg4LjE5OCIsImVtYWlsIjoic2VydmljZS1hY2NvdW50LTk2OWU4Y2M3LTI3N2UtNDFhMi1hNmJiLTdhODE1ZGNlMzE2OEBwbGFjZWhvbGRlci5vcmcifQ.TPglyLWZTWz6JbJ7q99FdLGRj5o0nH-Awiia1Ee_7nGNtQ4Q7WQWKgYL9zfXgOGwIPAEjAx9lyp9OG5sJgInbt6mstmxaERgPpF7yKq4LiqvnZuaH8k6n-Jq5Z3kp5c6JmEbYNmcjm0PbsEKOBdmfG-WIOqDqUOEhmhwcU30bKMyXrtnNLoThHhezCbsF5Z8Iyg6SokPQGq3ewN9e9_VbnJTozxaWAyNusqr3ut6Eb310o8lD8MHLXx2zSYwy-iOA_-WOsNGqcNU9mM-_Ja96vjcleEAwUU9WCTOh_fCrsfkNCj-jRE6XCGnnYjVf-0t2esbmLzGKq-FFHa_1-Eo2A";
    public static final String API_KEY = "Authorization";

    private final RestTemplate restTemplate;

    public Object getDataFromParkingSensors() {
        String url = BOSCH_API_URL;

        HttpHeaders headers = getHttpHeaders();
        ResponseEntity<Object> responseEntity;

        HttpEntity request = new HttpEntity<>(headers);

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        } catch (Exception ex) {
            log.error("Something goes wrong", ex);
            responseEntity = null;
        }
        return responseEntity;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(API_KEY, API_KEY_VALUE);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
