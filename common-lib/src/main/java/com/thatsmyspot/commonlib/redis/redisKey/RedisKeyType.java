package com.thatsmyspot.commonlib.redis.redisKey;

import lombok.Getter;

@Getter
public enum RedisKeyType {
    // User related keys
    USER_ID("user:<id>"),
    USER_EMAIL("user:<email>"),
    USER_USERNAME("user:<username>");

    private final String keyTemplate;

    RedisKeyType(String keyTemplate) {
        this.keyTemplate = keyTemplate;
    }
}
