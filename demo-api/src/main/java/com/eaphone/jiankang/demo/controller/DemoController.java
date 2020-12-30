package com.eaphone.jiankang.demo.controller;


import com.eaphone.jiankang.demo.core.document.Order;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.util.query.OrderPageParam;
import com.eaphone.smarthealth.model.GeneralFlatPagedResponse;
import com.eaphone.smarthealth.model.GeneralResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 订单controller
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class DemoController extends BaseDemoController {
    @Autowired
    private DemoService demoService;


    /**
     * 保存新的订单
     *
     * @param order 新的订单
     * @return 是否成功
     */
    @PostMapping("/")
    public GeneralResponse<Boolean> saveOrder(@RequestBody Order order) {
        demoService.insert(order);
        return GeneralResponse.success(true);
    }

    /**
     * 修改指定用户的订单地址
     *
     * @param order 修改订单地址
     * @return 是否修改成功
     */
    @PatchMapping("/{orderId:[0-9a-z]{24}}/")
    public GeneralResponse<Object> updateByUserIdAndOrderId(@PathVariable String orderId
            ,@RequestBody Order order) {
        order.setOrderId(orderId);
        return GeneralResponse.success(demoService.updateByUserIdAndOrderId(order));
    }

    /**
     * 订单分页
     *
     * @param orderPageParam 条件封装
     * @return 当页数据
     */
    @GetMapping("/page/")
    public GeneralResponse<Map<String, Object>> getOrderPage(OrderPageParam orderPageParam) {
        return GeneralResponse.success(demoService.getOrderPage(orderPageParam));
    }

    /**
     * 订单分页
     * @param pageable 分页参数
     * @return
     */
    @GetMapping("/pageTwo/")
    public GeneralResponse<Page<Order>> pageTwo(@ModelAttribute("page") Pageable pageable){
        return GeneralFlatPagedResponse.success(demoService.pageTwo(pageable));
    }

    /**
     * 订单状态更新
     *
     * @param orderId     订单id
     * @param status      需修改的状态
     * @return
     */
    @PatchMapping("/{orderId}/status/{status}/")
    public GeneralResponse<Boolean> updateOrderStatus(@ModelAttribute("token")String token
                                                     ,@PathVariable String orderId
                                                     ,@PathVariable Integer status) {
        String userId = checkUser(token);
        boolean bool = demoService.updateStatus(orderId, status);
        System.out.println(userId+"  "+bool);
        return GeneralResponse.success(bool);
    }
}
