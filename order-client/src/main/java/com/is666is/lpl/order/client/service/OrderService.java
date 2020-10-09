package com.is666is.lpl.order.client.service;

import com.eaphone.smarthealth.client.model.ClientResponse;
import com.is666is.lpl.order.client.dto.OrderResult;
import com.is666is.lpl.order.client.service.fallback.OrderServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 订单客户端
 */
@FeignClient(name = "order-api",contextId = "order",path = "/secret/order/",fallback = OrderServiceFallBack.class)
public interface OrderService {
    /**
     * 获取指定用户id所有订单
     * @param userId 用户id
     * @return  所有订单
     */
    @GetMapping("/{userId:[0-9a-z]{24}}/")
    ClientResponse<List<OrderResult>> findAllByUserId(@PathVariable String userId);

    /**
     * 获取指定订单状态
     * @param orderNumber 订单编号
     * @return 订单状态
     */
    @GetMapping("/status/{orderNumber:[0-9a-z]+}/")
    ClientResponse<Integer> findByOrderStatus(@PathVariable String orderNumber);
}
