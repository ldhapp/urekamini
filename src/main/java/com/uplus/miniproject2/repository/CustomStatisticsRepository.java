package com.uplus.miniproject2.repository;

import com.uplus.miniproject2.entity.proflie.RequestStatus;

import java.util.List;

public interface CustomStatisticsRepository {
    List<Object[]> findMajorCounts(RequestStatus pendingStatus, RequestStatus rejectedStatus);
    List<String> findDistinctMajors(RequestStatus pendingStatus, RequestStatus rejectedStatus);
    List<Object[]> findHobbyCounts(RequestStatus pendingStatus, RequestStatus rejectedStatus);
    List<Object[]> findRegionCounts(RequestStatus pendingStatus, RequestStatus rejectedStatus);
}
