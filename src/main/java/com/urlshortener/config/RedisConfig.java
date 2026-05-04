package com.urlshortener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/*
 * Configuration class for Redis
 * Spring Boot automatically detects Redis running on localhost:6379
 */

@Configuration
public class RedisConfig {

    /*
     * Redis template used to interact with Redis
     * Key -> String
     * Value -> String
     */

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {

        return new StringRedisTemplate(connectionFactory);
    }
}