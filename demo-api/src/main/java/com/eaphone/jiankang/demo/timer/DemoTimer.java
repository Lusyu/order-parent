package com.eaphone.jiankang.demo.timer;

import com.eaphone.jiankang.demo.amqp.AmqpUtil;
import com.eaphone.jiankang.demo.amqp.message.DemoMessage;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




/**
 * 调度任务业务处理器
 */
@Component
public class DemoTimer {
    @Autowired
    private AmqpUtil amqpUtil;

    @XxlJob("demoHandler")
    public ReturnT<String> demoHandler(String param){
        XxlJobLogger.log("xxl-job 开始执行");
        DemoMessage demoMessage = new DemoMessage();
        demoMessage.setId("123...");
        demoMessage.setName("max");
        demoMessage.setPrice(10000f);
        amqpUtil.sendDemoMessage(demoMessage);
        XxlJobLogger.log("结束...");
        return ReturnT.SUCCESS;
    }
}
