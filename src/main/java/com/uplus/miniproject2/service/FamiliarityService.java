package com.uplus.miniproject2.service;

import com.uplus.miniproject2.dto.FamiliarityRankingDto;
import com.uplus.miniproject2.dto.FamiliarityResultDto;
import com.uplus.miniproject2.entity.familiarity.FamiliarityScore;
import com.uplus.miniproject2.entity.hobby.Hobby;
import com.uplus.miniproject2.entity.proflie.MBTI;
import com.uplus.miniproject2.entity.proflie.Profile;
import com.uplus.miniproject2.entity.user.User;
import com.uplus.miniproject2.repository.FamiliarityScoreRepository;
import com.uplus.miniproject2.repository.ProfileRepository;
import com.uplus.miniproject2.repository.UserRepository;
import com.uplus.miniproject2.util.FamiliarityCalculator;
import com.uplus.miniproject2.util.HobbyFamiliarityCalculator;
import com.uplus.miniproject2.util.MbtiFamiliarity;
import com.uplus.miniproject2.util.NameFamiliarityCalculator;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FamiliarityService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final FamiliarityRankService familiarityRankService;

    public Page<FamiliarityRankingDto> calculateFamiliarityRanking(Long loginUserId, Pageable pageable) {
        User loginUser = userRepository.findByIdWithProfileAndHobbies(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException());

        if (loginUser.getProfile() == null) {
            throw new IllegalStateException("프로필 등록이 필요합니다.");
        }

        List<Profile> allApprovedProfile = profileRepository.findAllAcceptedProfile().stream()
                .filter(profile -> !profile.getUser().getId().equals(loginUserId))
                .toList();

        List<FamiliarityRankingDto> rankingList = allApprovedProfile.stream()
                .map(profile -> familiarityRankService.getFamiliarityResult(loginUser, profile))
                .sorted(Comparator.comparing(FamiliarityRankingDto::getFinalScore).reversed())
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), rankingList.size());
        List<FamiliarityRankingDto> pagedRankingList = rankingList.subList(start, end);

        long total = rankingList.size();

        return new PageImpl<>(pagedRankingList, pageable, total);
    }

    public FamiliarityResultDto calculateFamiliarity(Long loginUserId, Long clickedUserId) {
        User loginUser = userRepository.findByIdWithProfileAndHobbies(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException());

        User clickedUser = userRepository.findByIdWithProfileAndHobbies(clickedUserId)
                .orElseThrow(() -> new IllegalArgumentException());

        if (loginUser.getProfile() == null) {
            throw new IllegalStateException("프로필 등록이 필요합니다.");
        }

        double nameFamiliarity = FamiliarityCalculator.calculateNameFamiliarity(loginUser.getName(), clickedUser.getName());
        double mbtiFamiliarity = FamiliarityCalculator.getMbtiFamiliarity(loginUser.getProfile().getMbti(),
                clickedUser.getProfile().getMbti());
        double hobbyFamiliarity = FamiliarityCalculator.calculateHobbyFamiliarity(loginUser.getProfile().getHobbies(),
                clickedUser.getProfile().getHobbies());
        double finalScore = nameFamiliarity * 0.15 + mbtiFamiliarity * 0.6 + hobbyFamiliarity * 0.25;

        return new FamiliarityResultDto(nameFamiliarity, mbtiFamiliarity, hobbyFamiliarity, finalScore);
    }
}
