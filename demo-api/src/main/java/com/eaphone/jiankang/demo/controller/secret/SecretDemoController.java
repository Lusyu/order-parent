package com.eaphone.jiankang.demo.controller.secret;

import com.eaphone.jiankang.demo.core.document.Demo;
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
    public GeneralResponse<List<Demo>> findAllByUserId(@PathVariable String userId) {
        return GeneralResponse.success(demoService.findAllByUserId(userId));
    }

    /**
     * 获取指定订单状态
     *
     * @param orderId 订单编号
     * @return 状态
     */
    @GetMapping("/status/{orderId:[0-9a-z]+}/")
    public GeneralResponse<Integer> findByOrderId(@PathVariable String orderId) {
        return GeneralResponse.success(demoService.findFirstByOrderId(orderId));
    }

    /**
     * 生成订单
     * @param demo 新订单
     * @return 订单是否生成成功
     */
    @PostMapping("/")
    public GeneralResponse<Boolean> saveOrder(Demo demo){
        return  GeneralResponse.success(demoService.save(demo)!=null);
    }

    /**
     * 更新订单
     * @param demo 需修改的订单
     * @return      订单是否修改成功
     */
    @PatchMapping("/")
    public GeneralResponse<Boolean> updateOrder(Demo demo){
        return GeneralResponse.success(demoService.updateOrder(demo));
    }
}
