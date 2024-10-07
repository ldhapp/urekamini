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

/**
 * @Project : 유레카 미니 프로젝트 2
 * @package_name        : com.uplus.miniproject2.service
 * @FileName : UserService
 * @since : 2024. 09. 27.
 * @version 1.0
 * @author : 이도현
 * @프로그램 설명 : 유저 아이디 값을 통해 해당 아이디 값 유저의 프로필을 조회하거나, 검색 조건에 따른 유저 정보 및 프로필을 조회할 수 있다.
 * @see com.uplus.miniproject2.mapper.UserMapper
 * @see com.uplus.miniproject2.repository.CustomUserRepository
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일        수정자      수정내용
 *  ----------   --------   ---------------------------
 *  2024.09.27   이도현      최초생성
 *  2024.09.27   이도현      검색 조건 별 조회 구현
 *  2024.10.01   이도현      QueryDSL -> MyBatis로 수정
 *  </pre>
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final CustomUserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * @Method Name : searchUser
     * @date : 2024. 09. 27.
     * @Method 설명 :
     * <p>
     * 이름, MBTI, 전공, 성별 값을 검색어로 해당하는 유저를 Repository를 통해 조회한다.
     * </p>
     * @param name: 검색으로 사용할 수 있는 이름 (이름에 해당 글자가 포함되면 검색)
     * @param mbti: 검색으로 사용할 수 있는 MBTI (16개의 MBTI중 1개 선택)
     * @param major: 검색으로 사용할 수 있는 전공 (전공에 해당 글자가 포함되면 검색)
     * @param gender: 검색으로 사용할 수 있는 성별값 (남, 여중 택 1)
     * @return Page<MainPageUserDto>: 페이지네이션을 위해 조회 결과 DTO 값을 Page객체에 담아 반환한다.
     */
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
