package com.eaphone.jiankang.demo.client.service;

import com.eaphone.jiankang.demo.client.service.fallback.DemoServiceFallBack;
import com.eaphone.smarthealth.client.model.ClientResponse;
import com.eaphone.jiankang.demo.client.dto.DemoResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * demo client
 */
@FeignClient(name = "demo-api",contextId = "demo",path = "/secret/demo/",fallback = DemoServiceFallBack.class)
public interface DemoService {
    /**
     * 获取指定用户的所有demo
     * @param userId 用户id
     * @return  demo
     */
    @GetMapping("/{userId:[0-9a-z]{24}}/demos/")
    ClientResponse<List<DemoResult>> findUserDemos(@PathVariable String userId);

    /**
     * 获取指定demo状态
     * @param demoId
     * @return 状态
     */
    @GetMapping("/status/{demoId:[0-9a-z]+}/")
    ClientResponse<Integer> findDemoStatus(@PathVariable String demoId);
}
