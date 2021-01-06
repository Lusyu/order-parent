package com.eaphone.jiankang.demo.client.service;

import com.eaphone.jiankang.demo.client.dto.DemoDto;
import com.eaphone.jiankang.demo.client.service.fallback.DemoServiceFallBack;
import com.eaphone.jiankang.demo.client.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.client.vo.DemoVo;
import com.eaphone.smarthealth.client.model.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * demo client
 */
@FeignClient(name = "demo-api", contextId = "demo", path = "/secret/demo/", fallback = DemoServiceFallBack.class)
public interface DemoService {
    /**
     * 新增demo
     *
     * @param demoDto
     * @return
     */
    @PostMapping("/")
    ClientResponse<Boolean> saveDemo(@RequestBody DemoDto demoDto);

    /**
     * 删除demo
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:[0-9a-z]{24}}/")
    ClientResponse<Boolean> deleteDemoById(@PathVariable String id);

    /**
     * 根据id更新demo的名称和价格
     *
     * @param id
     * @param name  名称
     * @param price 价格
     * @return
     */
    @PatchMapping("/id/{id:[0-9a-z]{24}}/name/{name:.+}/price/{price:.+}/")
    ClientResponse<Boolean> updateDemoById(@PathVariable String id
            , @PathVariable String name
            , @PathVariable Float price);

    /**
     * 根据指定id获取demo详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:[0-9a-z]{24}}/")
    ClientResponse<DemoDetailsVo> findDemoById(@PathVariable String id);

    /**
     * 获取所有demo
     * @return
     */
    @GetMapping("/")
    ClientResponse<List<DemoVo>> findDemos();

}
