package com.eaphone.jiankang.demo.client;

import com.eaphone.jiankang.demo.client.dto.OrderResult;
import com.eaphone.jiankang.demo.client.service.DemoService;
import com.eaphone.smarthealth.client.model.ClientResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.List;

/**
 * 订单 Client
 */
@Slf4j
public class DemoClient {
    @Autowired
    private DemoService demoService;

     /**
     * 获取指定用户的所有订单
     * @param //userId  用户id
     * @return  用户的所有订单
     */
    public List<OrderResult> findAllByUserId(@NonNull String userId){
        List<OrderResult> list=Collections.EMPTY_LIST;
        try {
            ClientResponse<List<OrderResult>> response = demoService.findAllByUserId(userId);
            return  response.getData()!=null&&response.isSuccess()
                                        ? response.getData()
                                        : list;
        }catch (Exception e){
            log.info("findAllByUserId",e.getMessage());
        }
        return list;
    }

    /**
     * 根据指定订单状态
     * @param orderNumber 订单编号
     * @return 订单状态
     */
    public Integer findByOrderStatus(@NonNull String orderNumber){
        Integer status=-1;
        try {
            ClientResponse<Integer> response = demoService.findByOrderStatus(orderNumber);
            return response!=null&&response.isSuccess()?response.getData():status;
        }catch (Exception e){
            log.info("findByOrderStatus",e.getMessage());
        }
        return  status;
    }
}
