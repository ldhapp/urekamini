package com.uplus.miniproject2.repository;

import com.uplus.miniproject2.entity.familiarity.FamiliarityScore;
import com.uplus.miniproject2.entity.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FamiliarityScoreRepository extends JpaRepository<FamiliarityScore, Long> {
    Optional<FamiliarityScore> findByLoginedUserAndTargetUser(User loginedUser, User targetUser);

    @Query("SELECT fs.targetUser.id FROM FamiliarityScore fs WHERE fs.loginedUser.id = :loginUserId")
    List<Long> findAllTargetUserIdsForLoginUser(Long loginUserId);
}
