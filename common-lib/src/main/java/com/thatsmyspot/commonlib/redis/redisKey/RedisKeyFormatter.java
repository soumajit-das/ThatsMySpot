package com.thatsmyspot.commonlib.redis.redisKey;

import java.util.Map;

public class RedisKeyFormatter {
    public static String formatKey(RedisKeyType keyType, Map<String, Object> templateComponents) {
        String keyTemplate = keyType.getKeyTemplate();

        for (Map.Entry<String, Object> entry : templateComponents.entrySet()) {
            if (!keyTemplate.contains("<" + entry.getKey() + ">")) {
                throw new IllegalArgumentException("Redis key template does not contain component: " + entry.getKey());
            }
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("Redis template component value cannot be null");
            }
            if (entry.getValue() instanceof String && ((String) entry.getValue()).isEmpty()) {
                throw new IllegalArgumentException("Redis key template component value cannot be empty");
            }

            keyTemplate = keyTemplate.replace("<" + entry.getKey() + ">", entry.getValue().toString());
        }
        return keyTemplate;
    }
}
