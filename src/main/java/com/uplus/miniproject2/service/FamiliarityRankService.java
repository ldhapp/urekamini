package com.uplus.miniproject2.service;

import com.uplus.miniproject2.dto.FamiliarityRankingDto;
import com.uplus.miniproject2.dto.FamiliarityResultDto;
import com.uplus.miniproject2.entity.familiarity.FamiliarityScore;
import com.uplus.miniproject2.entity.proflie.Profile;
import com.uplus.miniproject2.entity.user.User;
import com.uplus.miniproject2.repository.FamiliarityScoreRepository;
import com.uplus.miniproject2.util.FamiliarityCalculator;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FamiliarityRankService {

    private final FamiliarityScoreRepository familiarityScoreRepository;

    @Cacheable(value = "familiarityScores", key = "#loginUser.getId() + '-' + #targetProfile.getId()")
    public FamiliarityRankingDto getFamiliarityResult(User loginUser, Profile targetProfile) {
        return calculateAndSaveFamiliarity(loginUser, targetProfile);
    }

    private FamiliarityRankingDto calculateAndSaveFamiliarity(User loginUser, Profile targetProfile) {
        User targetUser = targetProfile.getUser();
        String targetUserName = targetUser.getName();

        Optional<FamiliarityScore> existingScore = familiarityScoreRepository.findByLoginedUserAndTargetUser(
                loginUser, targetUser);

        if (existingScore.isPresent()) {
            FamiliarityScore score = existingScore.get();

            return FamiliarityRankingDto.builder()
                    .targetUserName(targetUserName)
                    .nameScore(score.getNameScore())
                    .mbtiScore(score.getMbtiScore())
                    .hobbyScore(score.getHobbyScore())
                    .finalScore(score.getFinalScore())
                    .targetUserImage(targetUser.getProfile().getImage())
                    .targetUserId(targetUser.getId())
                    .build();
        }

        double nameScore = FamiliarityCalculator.calculateNameFamiliarity(loginUser.getName(), targetUserName);
        double mbtiScore = FamiliarityCalculator.getMbtiFamiliarity(loginUser.getProfile().getMbti(), targetProfile.getMbti());
        double hobbyScore = FamiliarityCalculator.calculateHobbyFamiliarity(loginUser.getProfile().getHobbies(), targetProfile.getHobbies());
        double finalScore = nameScore * 0.15 + mbtiScore * 0.6 + hobbyScore * 0.25;

        FamiliarityScore newScore = FamiliarityScore.builder()
                .loginedUser(loginUser)
                .targetUser(targetUser)
                .nameScore(nameScore)
                .mbtiScore(mbtiScore)
                .hobbyScore(hobbyScore)
                .finalScore(finalScore)
                .lastCalculated(LocalDateTime.now())
                .build();

        familiarityScoreRepository.save(newScore);

        return FamiliarityRankingDto.builder()
                .targetUserName(targetUserName)
                .nameScore(nameScore)
                .mbtiScore(mbtiScore)
                .hobbyScore(hobbyScore)
                .finalScore(finalScore)
                .targetUserImage(targetUser.getProfile().getImage())
                .targetUserId(targetUser.getId())
                .build();
    }
}
