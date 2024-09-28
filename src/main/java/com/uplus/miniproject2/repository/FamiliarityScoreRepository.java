package com.uplus.miniproject2.repository;

import com.uplus.miniproject2.entity.familiarity.FamiliarityScore;
import com.uplus.miniproject2.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliarityScoreRepository extends JpaRepository<FamiliarityScore, Long> {
    Optional<FamiliarityScore> findByLoginedUserAndTargetUser(User loginedUser, User targetUser);
}
