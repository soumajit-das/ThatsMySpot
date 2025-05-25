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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
@Slf4j
public class RedisCacheUpdateAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @PostConstruct
    public void init() {
        System.out.println("RedisCacheUpdateAspect initialized");
    }

    @Around("@annotation(redisCacheUpdate)")
    public void redisCacheUpdate(ProceedingJoinPoint joinPoint, RedisCacheUpdate redisCache) throws Throwable {
        Map<String, Object> templateComponents = RedisAnnotationUtils.getTemplateComponentObjectMap(joinPoint, redisCache.templateComponent());
        String key = RedisKeyFormatter.formatKey(redisCache.keyType(), templateComponents);

        System.out.println("Called");
        if (redisTemplate.hasKey(key)) {
            Object result = joinPoint.proceed();

            if (result instanceof ResponseEntity<?> responseEntity) {
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    redisTemplate.opsForValue().set(
                            key,
                            objectMapper.writeValueAsString(responseEntity.getBody()),
                            redisCache.ttl(),
                            redisCache.timeUnit()
                    );
                    log.info("Updated redis cache for key: {}", key);
                }
            }
        }
    }
}
