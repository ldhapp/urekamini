package com.uplus.miniproject2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.miniproject2.entity.proflie.Region;
import com.uplus.miniproject2.repository.RegionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @PostConstruct
    public void init() {
        loadRegions();
    }

    private void loadRegions() {
        try {
            InputStream inputStream = new FileInputStream("path/to/regions.json");
            ObjectMapper objectMapper = new ObjectMapper();
            Region[] regions = objectMapper.readValue(inputStream, Region[].class);

            for (Region region : regions) {
                regionRepository.save(region);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
