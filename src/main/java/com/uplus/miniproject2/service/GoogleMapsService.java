package com.uplus.miniproject2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// 구글API 호출
@Service
public class GoogleMapsService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDirections(double lat1, double lon1, double lat2, double lon2) {
        String url = String.format("https://maps.googleapis.com/maps/api/directions/json?origin=%s,%s&destination=%s,%s&key=%s",
                lat1, lon1, lat2, lon2, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
