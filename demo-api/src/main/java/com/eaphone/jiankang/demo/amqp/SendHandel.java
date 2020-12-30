package com.eaphone.jiankang.demo.amqp;

import com.eaphone.jiankang.demo.config.DemoRabbitConfiguration;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq测试用例
 */
@Component
public class SendHandel {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@PostConstruct
    public void sendExchange(){
        EmbeddedProduct embeddedProduct = new EmbeddedProduct();
        embeddedProduct.setId("1");
        embeddedProduct.setName("12 pro max");
        embeddedProduct.setPrice(12222f);
        rabbitTemplate.convertAndSend(DemoRabbitConfiguration.ORDER_STATUS_ROUTING_KEY, embeddedProduct);
    }
}
