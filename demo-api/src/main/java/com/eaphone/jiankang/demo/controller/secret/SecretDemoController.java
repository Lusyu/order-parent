package com.eaphone.jiankang.demo.controller.secret;

import com.eaphone.jiankang.demo.core.document.Order;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.smarthealth.api.controller.SecretApi;
import com.eaphone.smarthealth.model.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 私密的 API
 */
@SecretApi
@RestController
@RequestMapping("/secret/demo/")
public class SecretDemoController {
    @Autowired
    private DemoService demoService;

    /**
     * 获取指定用户的所有订单
     *
     * @param userId 用户id
     * @return 订单
     */
    @GetMapping("/{userId:[0-9a-z]{24}}/demos/")
    public GeneralResponse<List<Order>> findAllByUserId(@PathVariable String userId) {
        return GeneralResponse.success(demoService.findAllByUserId(userId));
    }

    /**
     * 获取指定订单状态
     *
     * @param orderNumber 订单编号
     * @return 状态
     */
    @GetMapping("/status/{orderNumber:[0-9a-z]+}/")
    public GeneralResponse<Integer> findByOrderStatus(@PathVariable String orderNumber) {
        return GeneralResponse.success(demoService.findByOrderStatus(orderNumber));
    }

    /**
     * 生成订单
     * @param order 新订单
     * @return 订单是否生成成功
     */
    @PostMapping("/")
    public GeneralResponse<Boolean> saveOrder(Order order){
        return  GeneralResponse.success(demoService.save(order)!=null);
    }

    /**
     * 更新订单
     * @param order 需修改的订单
     * @return      订单是否修改成功
     */
    @PatchMapping("/")
    public GeneralResponse<Boolean> updateOrder(Order order){
        return GeneralResponse.success(demoService.updateOrder(order));
    }
}
