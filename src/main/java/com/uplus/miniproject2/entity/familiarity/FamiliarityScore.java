package com.uplus.miniproject2.entity.familiarity;

import com.uplus.miniproject2.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "familiarity_score")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamiliarityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User loginedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;

    private Double nameScore;
    private Double mbtiScore;
    private Double hobbyScore;
    private Double finalScore;

    private LocalDateTime lastCalculated;

    @Builder
    public FamiliarityScore(User loginedUser, User targetUser, Double nameScore, Double mbtiScore, Double hobbyScore,
                            Double finalScore, LocalDateTime lastCalculated) {
        this.loginedUser = loginedUser;
        this.targetUser = targetUser;
        this.nameScore = nameScore;
        this.mbtiScore = mbtiScore;
        this.hobbyScore = hobbyScore;
        this.finalScore = finalScore;
        this.lastCalculated = lastCalculated;
    }

    public void updateScore(Double newNameScore, Double newMbtiScore, Double newHobbyScore, Double newFinalScore) {
        this.nameScore = newNameScore;
        this.mbtiScore = newMbtiScore;
        this.hobbyScore = newHobbyScore;
        this.finalScore = newFinalScore;
        this.lastCalculated = LocalDateTime.now();
    }
}
