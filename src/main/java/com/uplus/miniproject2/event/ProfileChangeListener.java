package com.uplus.miniproject2.event;

import com.uplus.miniproject2.entity.user.User;
import com.uplus.miniproject2.repository.UserRepository;
import com.uplus.miniproject2.service.FamiliarityRankService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileChangeListener {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;

    @EventListener
    public void handleProfileChangeEvent(ProfileChangeEvent event) {
        Long profileId = event.getProfileId();

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            String key = "profileEvent";
            String field = "userId:" + user.getId() + ":profileId:" + profileId;
            redisTemplate.opsForHash().put(key, field, "false");
        }
    }
}
