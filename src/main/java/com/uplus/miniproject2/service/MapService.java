package com.uplus.miniproject2.service;

import com.uplus.miniproject2.dto.MapMarkerDto;
import com.uplus.miniproject2.entity.proflie.Profile;
import com.uplus.miniproject2.entity.proflie.Region;

import java.util.List;

public interface MapService {
    List<MapMarkerDto> findAllProfiles();

    // 지역 이름으로 지역 정보를 가져오는 메서드 추가
    Region findRegionByName(String name);
}
