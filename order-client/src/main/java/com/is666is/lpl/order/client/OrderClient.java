package com.is666is.lpl.order.client;

import com.eaphone.smarthealth.client.model.ClientResponse;
import com.is666is.lpl.order.client.dto.OrderResult;
import com.is666is.lpl.order.client.service.OrderService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.util.Collections;
import java.util.List;

/**
 * 订单 Client
 */
@Slf4j
public class OrderClient {
    @Autowired
    private OrderService orderService;

     /**
     * 获取指定用户的所有订单
     * @param //userId  用户id
     * @return  用户的所有订单
     */
    public List<OrderResult> findAllByUserId(@NonNull String userId){
        try {
            ClientResponse<List<OrderResult>> response = orderService.findAllByUserId(userId);
            return  response.getData()!=null&&response.isSuccess()
                                        ? response.getData()
                                        : Collections.emptyList();
        }catch (Exception e){
            log.info("findAllByUserId",e.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * 根据指定订单状态
     * @param orderNumber 订单编号
     * @return 订单状态
     */
    public Integer findByOrderStatus(@NonNull String orderNumber){
        try {
            ClientResponse<Integer> response = orderService.findByOrderStatus(orderNumber);
            return response!=null&&response.isSuccess()?response.getData():null;
        }catch (Exception e){
            log.info("findByOrderStatus",e.getMessage());
        }
        return  -1;
    }
}
