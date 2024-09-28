package com.uplus.miniproject2.repository;

import com.uplus.miniproject2.entity.proflie.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUserId(Long userId);

    @Query("SELECT p FROM Profile p "
            + "JOIN FETCH p.user "
            + "JOIN FETCH p.hobbies "
            + "JOIN ProfileRequest pr ON pr.profile = p "
            + "WHERE pr.requestStatus NOT IN ('PENDING', 'REJECTED')")
    List<Profile> findAllAcceptedProfile();

    @Query("SELECT COUNT(p) FROM Profile p "
            + "JOIN ProfileRequest pr ON pr.profile = p "
            + "WHERE pr.requestStatus = 'APPROVED'")
    long countApprovedProfiles();
}
