package com.uplus.miniproject2.service;

import com.uplus.miniproject2.dto.MapMarkerDto;
import com.uplus.miniproject2.entity.proflie.Profile;
import com.uplus.miniproject2.entity.proflie.Region;

import java.util.List;

public interface MapService {
    List<MapMarkerDto> findAllProfiles();
    Region findRegionByName(String name);
}
