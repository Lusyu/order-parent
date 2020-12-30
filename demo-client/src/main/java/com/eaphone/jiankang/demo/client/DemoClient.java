package com.eaphone.jiankang.demo.client;

import com.eaphone.jiankang.demo.client.dto.DemoResult;
import com.eaphone.jiankang.demo.client.service.DemoService;
import com.eaphone.smarthealth.client.model.ClientResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.List;

/**
 * Demo Client
 */
@Slf4j
public class DemoClient {
    @Autowired
    private DemoService demoService;

     /**
     * 获取指定用户的所有demo
     * @param userId  用户id
     * @return  指定用户的所有demo
     */
    public List<DemoResult> findUserDemos(@NonNull String userId){
        List<DemoResult> list=Collections.EMPTY_LIST;
        try {
            ClientResponse<List<DemoResult>> response = demoService.findUserDemos(userId);
            return  response.getData()!=null&&response.isSuccess()
                                        ? response.getData()
                                        : list;
        }catch (Exception e){
            log.info("findAllByUserId",e.getMessage());
        }
        return list;
    }

    /**
     * 获取指定demo状态
     * @param demoId
     * @return demo状态
     */
    public Integer findDemoStatus(@NonNull String demoId){
        Integer status=-1;
        try {
            ClientResponse<Integer> response = demoService.findDemoStatus(demoId);
            return response!=null&&response.isSuccess()?response.getData():status;
        }catch (Exception e){
            log.info("findByOrderStatus",e.getMessage());
        }
        return  status;
    }
}
