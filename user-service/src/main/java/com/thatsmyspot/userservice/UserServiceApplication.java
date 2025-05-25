package com.thatsmyspot.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableAspectJAutoProxy
public class UserServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
