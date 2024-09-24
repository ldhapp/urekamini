package com.uplus.miniproject2.entity.proflie;

import com.uplus.miniproject2.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;

@Entity
@Table(name = "profile_request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Builder
    public ProfileRequest(User user, Profile profile, RequestType requestType, RequestStatus requestStatus) {
        this.user = user;
        this.profile = profile;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
    }

    public void updateProfileRequest(ProfileRequest newProfileRequest) {
        this.user = newProfileRequest.user;
        this.profile = newProfileRequest.profile;
        this.requestType = newProfileRequest.requestType;
        this.requestStatus = newProfileRequest.requestStatus;
    }
  
  
      // 명확한 비즈니스 목적을 가진 메서드
    public void changeRequestStatus(RequestStatus status) {
        // 이메일 변경에 대한 추가 비즈니스 로직을 여기에 포함할 수 있음
        if (isValidRequestStatus(status)) {
            this.requestStatus = status;
        } else {
            throw new IllegalArgumentException("Invalid requestStatus");
        }
    }

    private boolean isValidRequestStatus(RequestStatus status) {
        // 주어진 status가 RequestStatus Enum의 값 중 하나인지 확인
        return status != null && Arrays.asList(RequestStatus.values()).contains(status);
    }
  
  

}

