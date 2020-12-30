package com.eaphone.jiankang.demo.timer;

import com.eaphone.jiankang.demo.core.service.DemoService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * 调度任务业务处理器
 */
@Component
public class DemoCountTime {

    @Autowired
    private DemoService demoService;

    @XxlJob("demoCountHandle")
    public ReturnT<String> demoCountHandle(String param){
        XxlJobLogger.log("开始执行获取Demo总数");
        String msg=param+" 总数为"+ demoService.countByQuery(new Query());
        System.out.println(msg);
        XxlJobLogger.log(param+" 总数为"+msg);
        XxlJobLogger.log("结束...");
        return ReturnT.SUCCESS;
    }
}
