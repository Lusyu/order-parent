package com.eaphone.jiankang.demo.client.service;

import com.eaphone.jiankang.demo.client.service.fallback.DemoServiceFallBack;
import com.eaphone.smarthealth.client.model.ClientResponse;
import com.eaphone.jiankang.demo.client.dto.OrderResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 订单client
 */
@FeignClient(name = "demo-api",contextId = "demo",path = "/secret/order/",fallback = DemoServiceFallBack.class)
public interface DemoService {
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
