package com.is666is.lpl.order.controller;

import com.eaphone.smarthealth.api.controller.BaseRestController;
import com.eaphone.smarthealth.model.GeneralResponse;
import com.is666is.lpl.order.core.document.Order;
import com.is666is.lpl.order.core.service.OrderService;
import com.is666is.lpl.order.core.util.query.OrderPageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 订单controller
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController extends BaseRestController {
    @Autowired
    private OrderService orderService;

    /**
     * 修改指定用户的订单信息
     *
     * @param order 修改订单
     * @return 是否修改成功
     */
    @PostMapping("/update/")
    public GeneralResponse<Object> updateByUserIdAndOrderId(@RequestBody Order order) {
        return GeneralResponse.success(orderService.updateByUserIdAndOrderId(order));
    }


    /**
     * 订单分页
     * @param orderPageParam 条件封装
     * @return 当页数据
     */
    @GetMapping("/page/")
    public GeneralResponse<Map<String,Object>>  getOrderPage(OrderPageParam orderPageParam){
        Map<String, Object> orderPage = orderService.getOrderPage(orderPageParam);
        return GeneralResponse.success(orderPage);
    }
}
