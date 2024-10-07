package com.uplus.miniproject2.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamiliarityEventService {

    private final RedisTemplate<String, Object> redisTemplate;

    public List<Long> getUnprocessedProfileIds(Long userId) {
        Set<Long> unprocessedProfileIds = new HashSet<>();  // 중복을 방지하기 위해 Set 사용

        Map<Object, Object> existProfileEvents = redisTemplate.opsForHash().entries("profileEvent");

        for (Map.Entry<Object, Object> entry : existProfileEvents.entrySet()) {
            String key = (String) entry.getKey();

            if (key.startsWith("userId:" + userId + ":") && "false".equals(entry.getValue())) {
                Long unprocessedProfileId = Long.parseLong(key.split(":profileId:")[1]);
                unprocessedProfileIds.add(unprocessedProfileId);
            }
        }

        return new ArrayList<>(unprocessedProfileIds);
    }

    public void updateClearEvent(Long userId, Long profileId) {
        String key = "profileEvent";
        String field = "userId:" + userId + ":profileId:" + profileId;
        redisTemplate.opsForHash().put(key, field, "true");
    }

    public boolean isEventProcessed(Long loginUserId, Long targetProfileId) {
        String key = "profileEvent";
        String field = "userId:" + loginUserId + ":profileId:" + targetProfileId;

        Object processed = redisTemplate.opsForHash().get(key, field);
        return processed != null && processed.equals("true");
    }
}
