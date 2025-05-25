package com.thatsmyspot.userservice.customAnnotations.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thatsmyspot.commonlib.redis.redisKey.RedisKeyFormatter;
import com.thatsmyspot.userservice.customAnnotations.redis.utils.RedisAnnotationUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
@Slf4j
public class RedisCacheDeleteAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @PostConstruct
    public void init() {
        System.out.println("RedisCacheDeleteAspect initialized");
    }

    @Around("@annotation(redisCacheDelete)")
    public void RedisCacheDelete(ProceedingJoinPoint joinPoint, RedisCacheDelete redisCache) throws Throwable {
        Map<String, Object> templateComponents = RedisAnnotationUtils.getTemplateComponentObjectMap(joinPoint, redisCache.templateComponent());
        String key = RedisKeyFormatter.formatKey(redisCache.keyType(), templateComponents);

        System.out.println("Called");
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            log.info("Deleted redis cache for key: {}", key);
        }
    }
}
