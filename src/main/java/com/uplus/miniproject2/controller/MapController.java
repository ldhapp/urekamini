package com.uplus.miniproject2.controller;

import com.uplus.miniproject2.dto.MapMarkerDto;
import com.uplus.miniproject2.entity.proflie.Region; // 추가: Region 엔티티
import com.uplus.miniproject2.service.MapService;
import com.uplus.miniproject2.util.ApiUtil;
import com.uplus.miniproject2.util.ApiUtil.ApiSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // 추가: PathVariable 임포트
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @GetMapping("/profiles")
    public ApiSuccess<?> getAllProfiles() {
        return ApiUtil.success(mapService.findAllProfiles());
    }

    // 지역 이름으로 지역 정보를 가져오는 메서드 추가
    @GetMapping("/regions/{name}")
    public ApiSuccess<Region> getRegionByName(@PathVariable String name) {
        Region region = mapService.findRegionByName(name);
        return ApiUtil.success(region);
    }
}
