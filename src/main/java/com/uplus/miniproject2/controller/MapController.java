package com.uplus.miniproject2.controller;

import com.uplus.miniproject2.entity.proflie.Region; // 추가: Region 엔티티
import com.uplus.miniproject2.repository.RegionRepository;
import com.uplus.miniproject2.service.MapService;
import com.uplus.miniproject2.util.ApiUtil;
import com.uplus.miniproject2.util.ApiUtil.ApiSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final RegionRepository regionRepository; // 추가: Repository 인스턴스
    private final RestTemplate restTemplate; // 추가: RestTemplate 인스턴스

    @GetMapping("/profiles")
    public ApiSuccess<?> getAllProfiles() {
        return ApiUtil.success(mapService.findAllProfiles());
    }

    // 지역 이름으로 지역 정보를 가져오는 메서드
    @GetMapping("/regions/{name}")
    public ApiSuccess<Region> findRegionByName(@PathVariable String name) {
        Region region = regionRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("지역을 찾을 수 없습니다: " + name));
        return ApiUtil.success(region);
    }
}
