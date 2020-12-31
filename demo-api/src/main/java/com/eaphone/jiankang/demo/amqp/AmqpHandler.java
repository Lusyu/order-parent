package com.eaphone.jiankang.demo.amqp;

import com.eaphone.jiankang.demo.config.DemoRabbitConfiguration;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * mq业务处理器
 */
@Component
public class AmqpHandler {

   @RabbitListener(queues= DemoRabbitConfiguration.DEMO_QUEUE_NAME)
    public void dealOrderStatus(EmbeddedDemo embeddedDemo){
        //.....
       System.out.println("收到消息  --------------"+ embeddedDemo);
    }
}
