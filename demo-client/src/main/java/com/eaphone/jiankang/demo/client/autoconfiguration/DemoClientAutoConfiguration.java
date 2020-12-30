package com.eaphone.jiankang.demo.client.autoconfiguration;

import com.eaphone.jiankang.demo.client.DemoClient;
import com.eaphone.jiankang.demo.client.service.DemoService;
import com.eaphone.jiankang.demo.client.service.fallback.DemoServiceFallBack;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(DemoClient.class)
@EnableFeignClients(basePackageClasses = DemoService.class)
@ComponentScan(basePackageClasses = DemoServiceFallBack.class)
public class DemoClientAutoConfiguration {

    @ConditionalOnMissingBean(DemoClient.class)
    @Bean
    public DemoClient orderClient() {
        return new DemoClient();
    }
}
