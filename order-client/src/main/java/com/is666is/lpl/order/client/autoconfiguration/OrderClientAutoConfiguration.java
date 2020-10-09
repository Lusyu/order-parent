package com.is666is.lpl.order.client.autoconfiguration;

import com.is666is.lpl.order.client.OrderClient;
import com.is666is.lpl.order.client.service.OrderService;
import com.is666is.lpl.order.client.service.fallback.OrderServiceFallBack;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(OrderClient.class)
@EnableFeignClients(basePackageClasses = OrderService.class)
@ComponentScan(basePackageClasses = OrderServiceFallBack.class)
public class OrderClientAutoConfiguration {

    @ConditionalOnMissingBean(OrderClient.class)
    @Bean
    public OrderClient orderClient() {
        return new OrderClient();
    }
}
