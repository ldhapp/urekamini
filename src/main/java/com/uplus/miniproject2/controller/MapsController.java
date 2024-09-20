package com.uplus.miniproject2.controller;

import com.uplus.miniproject2.service.GoogleMapsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapsController {

    private final GoogleMapsService googleMapsService;

    public MapsController(GoogleMapsService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping("/api/directions")
    public ResponseEntity<?> getDirections(@RequestParam double lat1, @RequestParam double lon1,
                                           @RequestParam double lat2, @RequestParam double lon2) {
        try {
            String directions = googleMapsService.getDirections(lat1, lon1, lat2, lon2);
            return ResponseEntity.ok(directions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving directions: " + e.getMessage());
        }
    }
}
