package com.eaphone.jiankang.demo.config;

import com.eaphone.smarthealth.amqp.config.DefaultRabbitConfiguration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 */
@Configuration
public class DemoRabbitConfiguration extends DefaultRabbitConfiguration {
    public static final String DEMO_QUEUE_NAME="demo.queue";
    public static final String DEMO_ROUTING_KEY="xla.xlb";

    @Bean
    public Queue queue(){
        return new Queue(DEMO_QUEUE_NAME,true);
    }
    @Bean
    public Binding exBindQueue(DirectExchange exchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(DEMO_ROUTING_KEY);
    }
}
