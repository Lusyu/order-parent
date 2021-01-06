package com.eaphone.jiankang.demo.controller.secret;

import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.dto.DemoDto;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.core.vo.DemoVo;
import com.eaphone.smarthealth.api.controller.SecretApi;
import com.eaphone.smarthealth.model.GeneralResponse;
import org.springframework.beans.BeanUtils;
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
     * 生成demo
     *
     * @param demoDto 新demo
     * @return demo是否生成成功
     */
    @PostMapping("/")
    public GeneralResponse<Boolean> saveDemo(@RequestBody DemoDto demoDto) {
        Demo demo = new Demo();
        BeanUtils.copyProperties(demoDto,demo);
        demoService.insert(demo);
        return GeneralResponse.success(true);
    }

    /**
     * 删除指定Demo
     *
     * @param
     * @return
     */
    @DeleteMapping("/{id:[0-9a-z]{24}}/")
    public GeneralResponse<Boolean> deleteDemoById(@PathVariable String id) {
        return GeneralResponse.success(demoService.deleteDemoById(id));
    }

    /**
     * 根据id更新demo的名称和价格
     *
     * @param id
     * @param name 名称
     * @param price 价格
     * @return
     */
    @PatchMapping("/id/{id:[0-9a-z]{24}}/name/{name:.+}/price/{price:.+}/")
    public GeneralResponse<Boolean> updateDemoById(@PathVariable String id
            , @PathVariable String name
            ,@PathVariable Float price) {
        return GeneralResponse.success(demoService.updateDemoNameById(id, name,price));
    }

    /**
     * 根据id获取指定的demo
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:[0-9a-z]{24}}/")
    public GeneralResponse<DemoDetailsVo> findDemoById(@PathVariable String id) {
        return GeneralResponse.success(demoService.findDemoById(id));
    }

    /**
     * 获取所有demo
     *
     * @param
     * @return demo
     */
    @GetMapping("/")
    public GeneralResponse<List<DemoVo>> findDemos() {
        return GeneralResponse.success(demoService.list());
    }
}
