package com.thatsmyspot.userservice.customAnnotations.redis.utils;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.HashMap;
import java.util.Map;

public class RedisAnnotationUtils {

    public static Map<String, Object> getTemplateComponentObjectMap(ProceedingJoinPoint joinPoint, String templateComponent) {
        Object[] args = joinPoint.getArgs();

        Map<String, Object> templateComponents = new HashMap<>();

        String[] templateComponentsArray = templateComponent.split("&&");
        for (String component : templateComponentsArray) {
            String[] keyValue = component.split("=");
            Object value = keyValue[1];
            if (keyValue[1].startsWith("$")) {
                int index = Integer.parseInt(keyValue[1].substring(1));
                value = args[index];
            }
            templateComponents.put(keyValue[0], value);
        }
        return templateComponents;
    }
}
