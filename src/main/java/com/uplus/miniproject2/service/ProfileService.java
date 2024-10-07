package com.uplus.miniproject2.service;

import com.uplus.miniproject2.dto.ProfileExistDto;
import com.uplus.miniproject2.dto.ProfilePageProfileRequestDto;
import com.uplus.miniproject2.dto.ProfilePageProfileResponseDto;
import com.uplus.miniproject2.entity.hobby.Hobby;
import com.uplus.miniproject2.dto.ProfileRequestDto;
import com.uplus.miniproject2.entity.proflie.*;
import com.uplus.miniproject2.entity.user.User;
import com.uplus.miniproject2.event.ProfileChangeEvent;
import com.uplus.miniproject2.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRequestRepository profileRequestRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final RegionRepository regionRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProfilePageProfileRequestDto createProfileRequest(Long userId,
                                                             ProfilePageProfileResponseDto profileResponseDto) {
        User user = userRepository.findByIdWithProfileAndHobbies(userId)
                .orElseThrow(() -> new IllegalArgumentException());

        RequestType requestType;
        Profile profile;
        Region region = regionRepository.findByName(profileResponseDto.getRegion());

        // 취미 이름 목록을 데이터베이스에서 한 번에 조회
        List<Hobby> allHobbies = hobbyRepository.findAllByNameIn(profileResponseDto.getHobbies());

        // 사용자의 기존 프로필이 있는지 확인
        if (user.getProfile() == null) {
            requestType = RequestType.REGISTER;
            // 프로필이 없는 경우 새로운 프로필 생성
            profile = Profile.builder()
                    .user(user)
                    .mbti(MBTI.valueOf(profileResponseDto.getMbti()))
                    .region(region)
                    .major(profileResponseDto.getMajor())
                    .plan(profileResponseDto.getPlan())
                    .niceExperience(profileResponseDto.getNiceExperience())
                    .hobbies(allHobbies)
                    .image(profileResponseDto.getProfileImage())
                    .build();

        } else {
            requestType = RequestType.UPDATE;
            // 기존 프로필이 있는 경우 업데이트
            profile = user.getProfile();

            profile.updateProfile(MBTI.valueOf(profileResponseDto.getMbti()), region, profileResponseDto.getMajor(),
                    profileResponseDto.getPlan(), profileResponseDto.getNiceExperience(),
                    allHobbies, profileResponseDto.getProfileImage());
        }

        ProfileRequest newProfileRequest = ProfileRequest.builder()
                .user(user)
                .profile(profile)
                .requestType(requestType)
                .requestStatus(RequestStatus.PENDING)
                .build();

        Optional<ProfileRequest> profileRequest = profileRequestRepository.findByUserId(userId);
        profileRequest.ifPresentOrElse(beforeProfileRequest -> {
                    beforeProfileRequest.updateProfileRequest(newProfileRequest);
                },

                () -> {
                    profileRequestRepository.save(newProfileRequest);
                });

        return new ProfilePageProfileRequestDto(
                profile.getMajor(),
                profile.getMbti().name(),
                profile.getRegion().getName(),
                profile.getPlan(),
                profile.getNiceExperience(),
                profile.getImage(),
                profile.getHobbies(),
                newProfileRequest.getRequestType(),
                newProfileRequest.getRequestStatus()
        );
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public Page<ProfileRequestDto> getProfileRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProfileRequestDto> profileRequests = profileRequestRepository.findAllDto(pageable);

        return profileRequests;
    }

    public ProfileExistDto getProfile(Long loginUserId) {

        Profile loginUserProfile = profileRepository.findByUserId(loginUserId);

        ProfileExistDto profileExistDto = new ProfileExistDto();

        if (loginUserProfile == null) {
            profileExistDto.setExist(false);
        } else {
            ProfileRequest pr = profileRequestRepository.findByUserId(loginUserId)
                    .orElseThrow(() -> new IllegalArgumentException());

            if (pr.getRequestStatus().isNotApproved()) {
                profileExistDto.setExist(false);
            } else {
                profileExistDto.setExist(true);
            }
        }

        return profileExistDto;
    }

    public void updateProfileRequest(Long requestId, String requestStatus) {
        // requestId에 해당하는 ProfileRequest 엔티티를 조회
        ProfileRequest request = profileRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid requestId: " + requestId));

        // requestStatus 문자열을 RequestStatus Enum으로 변환
        RequestStatus status;
        try {
            status = RequestStatus.valueOf(requestStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid requestStatus: " + requestStatus);
        }

        // 상태 변경
        request.changeRequestStatus(status);
        // 변경 사항 저장 (보통 Service에서 Repository를 사용하여 저장)
        profileRequestRepository.save(request);

        if (status == RequestStatus.APPROVED) {
            Profile profile = request.getProfile();
            eventPublisher.publishEvent(new ProfileChangeEvent(profile.getId(), request.getUser().getId(),
                    request.getRequestType().name()));
        }
    }
}
