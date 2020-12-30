package com.eaphone.jiankang.demo.core.config;

import com.eaphone.smarthealth.redis.service.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoCoreConfiguration {
   @Bean
    public RedisService redisService(){
        return new RedisService();
    }
}
