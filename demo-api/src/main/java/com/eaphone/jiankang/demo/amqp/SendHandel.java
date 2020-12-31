package com.eaphone.jiankang.demo.amqp;

import com.eaphone.jiankang.demo.config.DemoRabbitConfiguration;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * mq测试用例
 */
@Component
public class SendHandel {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@PostConstruct
    public void sendExchange(){
        EmbeddedDemo embeddedDemo = new EmbeddedDemo();
        embeddedDemo.setId("1");
        embeddedDemo.setName("12 pro max");
        embeddedDemo.setPrice(12222f);
        rabbitTemplate.convertAndSend(DemoRabbitConfiguration.DEMO_ROUTING_KEY, embeddedDemo);
    }
}
