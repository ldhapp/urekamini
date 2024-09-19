package com.uplus.miniproject2.service;

import com.uplus.miniproject2.dto.DetailPageUserDto;
import com.uplus.miniproject2.dto.MainPageUserDto;
import com.uplus.miniproject2.entity.proflie.Profile;
import com.uplus.miniproject2.entity.user.User;
import com.uplus.miniproject2.mapper.UserMapper;
import com.uplus.miniproject2.repository.CustomUserRepository;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final CustomUserRepository userRepository;
    private final UserMapper userMapper;

    public Page<MainPageUserDto> searchUser(String name, String mbti, String major, String gender, Pageable pageable) {
        return userRepository.findBySearchCondition(name, mbti, major, gender, pageable);
    }

    public Page<MainPageUserDto> searchUserByMyBatis(String name, String mbti, String major, String gender, Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();

        List<MainPageUserDto> results = userMapper.findBySearchCondition(name, mbti, major, gender, offset, pageSize);
        long totalCount = userMapper.countBySearchCondition(name, mbti, major, gender);

        return new PageImpl<>(results, pageable, totalCount);
    }

    public DetailPageUserDto getUserDetail(Long userId) {
        User user = userRepository.findById(userId);
        Profile profile = user.getProfile();

        return new DetailPageUserDto(
                user.getName(),
                user.getGender(),
                profile.getMbti().name(),
                profile.getRegion().getName(),
                profile.getMajor(),
                profile.getHobbies(),
                profile.getNiceExperience(),
                profile.getPlan(),
                profile.getImage()
        );
    }
}
