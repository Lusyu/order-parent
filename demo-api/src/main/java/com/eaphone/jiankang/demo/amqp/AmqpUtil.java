package com.eaphone.jiankang.demo.amqp;

import com.eaphone.jiankang.demo.amqp.message.DemoMessage;
import com.eaphone.jiankang.demo.config.DemoRabbitConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq发送工具类
 */
@Component
public class AmqpUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendDemoMessage(DemoMessage demoMessage){
        rabbitTemplate.convertAndSend(DemoRabbitConfiguration.DEMO_ROUTING_KEY,demoMessage);
    }
}
