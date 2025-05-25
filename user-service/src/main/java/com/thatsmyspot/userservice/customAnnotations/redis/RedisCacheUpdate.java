package com.thatsmyspot.userservice.customAnnotations.redis;

import com.thatsmyspot.commonlib.redis.redisKey.RedisKeyType;
import org.springframework.aot.hint.annotation.Reflective;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Reflective
public @interface RedisCacheUpdate {
    RedisKeyType keyType();
    String templateComponent(); // e.g.: "id=$1&&username=$2&&email=example@example.example"
    int ttl() default 60;
    TimeUnit timeUnit() default TimeUnit.MINUTES;
    Class<?> context() default Object.class;
}
