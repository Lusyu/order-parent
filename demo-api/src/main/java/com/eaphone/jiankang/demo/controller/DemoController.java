package com.eaphone.jiankang.demo.controller;


import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.util.query.DemoPageParam;
import com.eaphone.smarthealth.model.GeneralFlatPagedResponse;
import com.eaphone.smarthealth.model.GeneralResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单controller
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController extends BaseDemoController {
    @Autowired
    private DemoService demoService;

    /**
     * 保存新的demo
     *
     * @param demo
     * @return 是否成功
     */
    @PostMapping("/")
    public GeneralResponse<Boolean> saveOrder(@RequestBody Demo demo) {
        demoService.insert(demo);
        return GeneralResponse.success(true);
    }

    /**
     * 修改指定demo地址
     *
     * @param demoId
     * @param address 新地址
     * @return
     */
    @PatchMapping("/{demoId:[0-9a-z]{24}}/address/")
    public GeneralResponse<Boolean> updateDemoAddress(@PathVariable String demoId
            ,@RequestParam String address) {
        return GeneralResponse.success(demoService.updateDemoAddress(demoId,address));
    }

    /**
     * demo分页
     *
     * @param demoPageParam 条件封装
     * @return 当页数据
     */
    @GetMapping("/page/")
    public GeneralResponse<Map<String, Object>> getDemoPage(DemoPageParam demoPageParam) {
        return GeneralResponse.success(demoService.getDemoPage(demoPageParam));
    }

    /**
     * demo分页
     * @param pageable 分页参数
     * @return
     */
    @GetMapping("/pageTwo/")
    public GeneralResponse<Page<Demo>> pageTwo(@ModelAttribute("page") Pageable pageable){
        return GeneralFlatPagedResponse.success(demoService.pageTwo(pageable));
    }

    /**
     * demo状态更新
     *
     * @param demoId
     * @param status      需修改的状态
     * @return
     */
    @PatchMapping("/{demoId:[0-9a-z]{24}}/status/{status:[0-6]{1}}/")
    public GeneralResponse<Boolean> updateDemoStatus(@ModelAttribute("token")String token
                                                     ,@PathVariable String demoId
                                                     ,@PathVariable Integer status) {
        String userId = checkUser(token);
        boolean bool = demoService.updateStatus(demoId, status);
        return GeneralResponse.success(bool);
    }
}
