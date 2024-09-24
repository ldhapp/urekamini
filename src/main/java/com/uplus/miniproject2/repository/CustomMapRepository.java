package com.uplus.miniproject2.repository;

import com.uplus.miniproject2.dto.MapMarkerDto;
import java.util.List;

public interface CustomMapRepository {

    // 모든 사용자에 대한 지도 마커 정보를 가져오는 메소드
    List<MapMarkerDto> findAllMarkers();
}
