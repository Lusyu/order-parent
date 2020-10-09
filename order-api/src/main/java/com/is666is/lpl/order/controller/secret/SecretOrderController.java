package com.is666is.lpl.order.controller.secret;

import com.eaphone.smarthealth.api.controller.SecretApi;
import com.eaphone.smarthealth.model.GeneralResponse;
import com.is666is.lpl.order.core.document.Order;
import com.is666is.lpl.order.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 私密的 订单 API
 */
@SecretApi
@RestController
@RequestMapping("/secret/order/")
public class SecretOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取指定用户的所有订单
     *
     * @param userId 用户id
     * @return 订单
     */
    @GetMapping("/{userId:[0-9a-z]{24}}/")
    public GeneralResponse<List<Order>> findAllByUserId(@PathVariable String userId) {
        return GeneralResponse.success(orderService.findAllByUserId(userId));
    }

    /**
     * 获取指定订单状态
     * @param orderNumber 订单编号
     * @return 状态
     */
    @GetMapping("/status/{orderNumber:[0-9a-z]+}/")
    public GeneralResponse<Integer> findByOrderStatus(@PathVariable String orderNumber){
        return GeneralResponse.success(orderService.findByOrderStatus(orderNumber)) ;
    }
}
