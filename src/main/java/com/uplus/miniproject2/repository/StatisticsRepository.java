package com.uplus.miniproject2.repository;

import com.uplus.miniproject2.entity.proflie.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p JOIN ProfileRequest pr ON pr.profile = p "
            + "WHERE pr.requestStatus NOT IN ('PENDING', 'REJECTED')")
    List<Profile> findAll();

    // 전공별 수를 가져오는 쿼리
    @Query("SELECT p.major AS major, COUNT(p) AS count " +
            "FROM Profile p " +
            "JOIN ProfileRequest pr ON pr.profile = p " +
            "WHERE pr.requestStatus NOT IN ('PENDING', 'REJECTED') " +
            "GROUP BY p.major")
    List<Object[]> findMajorCounts();

    // 전공 목록 출력 (중복 제거)
    @Query("SELECT DISTINCT p.major FROM Profile p " +
            "JOIN ProfileRequest pr ON pr.profile = p " +
            "WHERE pr.requestStatus NOT IN ('PENDING', 'REJECTED')")
    List<String> findDistinctMajors();

    // 취미
    @Query("SELECT h.name, COUNT(p) AS count " +
            "FROM Profile p " +
            "JOIN ProfileRequest pr ON pr.profile = p " +
            "JOIN p.hobbies h " +
            "WHERE pr.requestStatus NOT IN ('PENDING', 'REJECTED') " +
            "GROUP BY h.name")
    List<Object[]> findHobbyCounts();

    // 거주지역
    @Query("SELECT p.region.name, COUNT(p) AS count " +
            "FROM Profile p " +
            "JOIN ProfileRequest pr ON pr.profile = p " +
            "WHERE pr.requestStatus NOT IN ('PENDING', 'REJECTED') " +
            "GROUP BY p.region.name")
    List<Object[]> findRegionCounts();

}