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
     * 获取指定用户的所有demo
     *
     * @param userId 用户id
     * @return demo
     */
    @GetMapping("/{userId:[0-9a-z]{24}}/demos/")
    public GeneralResponse<List<Demo>> findUserDemos(@PathVariable String userId) {
        return GeneralResponse.success(demoService.findAllByUserId(userId));
    }

    /**
     * 获取指定demo状态
     *
     * @param demoId
     * @return 状态
     */
    @GetMapping("/status/{demoId:[0-9a-z]+}/")
    public GeneralResponse<Integer> findDemoStatus(@PathVariable String demoId) {
        return GeneralResponse.success(demoService.findDemoStatusByDemoId(demoId));
    }

    /**
     * 生成demo
     *
     * @param demo 新demo
     * @return demo是否生成成功
     */

    @PostMapping("/")
    public GeneralResponse<Boolean> saveDemo(@RequestBody Demo demo) {
        return GeneralResponse.success(demoService.save(demo) != null);
    }

    /**
     * 更新demo
     *
     * @param demo 需修改的demo
     * @return     demo是否修改成功
     */
    @PatchMapping("/{id:[a-z0-9]+}/")
    public GeneralResponse<Boolean> updateDemo(@PathVariable String id, @RequestBody Demo demo) {
        if (demo == null) {
            return GeneralResponse.fail("demo不能为空!");
        }
        demo.setId(id);
        return GeneralResponse.success(demoService.updateDemo(demo));
    }
}
