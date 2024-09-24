package com.uplus.miniproject2.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Projections;
import com.uplus.miniproject2.dto.MapMarkerDto;
import com.uplus.miniproject2.entity.proflie.QProfile;
import com.uplus.miniproject2.entity.proflie.QProfileRequest;
import com.uplus.miniproject2.entity.proflie.RequestStatus;
import com.uplus.miniproject2.entity.user.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MapRepositoryImpl implements CustomMapRepository {

    // JPAQueryFactory를 통해 쿼리를 작성할 수 있도록 설정
    private final JPAQueryFactory queryFactory;

    // 사용자 정보를 지도 마커용으로 반환하는 메소드
    @Override
    public List<MapMarkerDto> findAllMarkers() {
        QUser user = QUser.user; // QUser: User 엔티티의 QueryDSL 객체
        QProfile profile = QProfile.profile; // QProfile: Profile 엔티티의 QueryDSL 객체
        QProfileRequest profileRequest = QProfileRequest.profileRequest; // QProfileRequest: ProfileRequest 엔티티의 QueryDSL 객체

        BooleanBuilder builder = new BooleanBuilder(); // 동적으로 조건을 추가할 수 있는 BooleanBuilder 생성

        // PENDING이나 REJECTED 상태가 아닌 프로필 요청만 조회하는 필터 조건 추가
        builder.and(profileRequest.requestStatus.isNotNull()) // 요청 상태가 NULL이 아닌 경우
                .and(profileRequest.requestStatus.ne(RequestStatus.PENDING)) // PENDING 상태가 아닌 경우
                .and(profileRequest.requestStatus.ne(RequestStatus.REJECTED)); // REJECTED 상태가 아닌 경우

        // 쿼리를 실행하여 사용자 정보를 지도 마커용으로 가져옴
        return queryFactory
                .select(Projections.constructor(MapMarkerDto.class, // MapMarkerDto로 데이터를 반환
                        user.id, // 사용자 ID
                        user.name, // 사용자 이름
                        profile.region.name, // 사용자의 지역 이름
                        profile.region.latitude, // 사용자의 지역 위도
                        profile.region.longitude // 사용자의 지역 경도
                ))
                .from(user) // User 테이블로부터 데이터를 가져옴
                .join(user.profile, profile) // User와 Profile 간의 조인
                .join(user.profileRequests, profileRequest) // User와 ProfileRequest 간의 조인
                .on(profileRequest.profile.eq(profile)) // ProfileRequest가 해당 프로필과 일치하는 경우만 선택
                .where(builder) // 동적으로 생성된 필터 조건을 적용
                .orderBy(user.id.asc()) // 사용자 ID 오름차순으로 정렬
                .fetch(); // 쿼리를 실행하고 결과를 가져옴
    }
}
