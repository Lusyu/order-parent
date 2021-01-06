package com.eaphone.jiankang.demo.controller;

import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.dto.DemoDto;
import com.eaphone.jiankang.demo.core.dto.DemoChildrenDto;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.core.vo.DemoVo;
import com.eaphone.smarthealth.model.GeneralFlatPagedResponse;
import com.eaphone.smarthealth.model.GeneralResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo controller
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
     * @param demoDto
     * @return 是否成功
     */
    @PostMapping("/")
    public GeneralResponse<Boolean> saveDemo(@ModelAttribute("token") String token
            , @RequestBody DemoDto demoDto) {
        checkUser(token);
        Demo demo = new Demo();
        BeanUtils.copyProperties(demoDto, demo);
        demoService.insert(demo);
        return GeneralResponse.success(true);
    }

    /**
     * 删除 demo
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:[0-9a-z]{24}}/")
    public GeneralResponse<Boolean> deleteDemoById(@ModelAttribute("token") String token
            , @PathVariable String id) {
        checkUser(token);
        return GeneralResponse.success(demoService.deleteDemoById(id));
    }

    /**
     * 更新嵌套字段
     *
     * @param id
     * @param demoChildren 新值
     * @return
     */
    @PatchMapping("/id/{id:[0-9a-z]{24}}/updateChildren/")
    public GeneralResponse<Boolean> updateEmbedDemo(@ModelAttribute("token") String token
            , @PathVariable String id
            , @RequestBody DemoChildrenDto demoChildren) {
        checkUser(token);
        return GeneralResponse.success(demoService.updateEmbedDemo(id, demoChildren));
    }

    /**
     * 对指定demo新增嵌套数据
     *
     * @param token
     * @param id
     * @param demoChildrenList 嵌套的值集
     * @return
     */
    @PatchMapping("/id/{id:[0-9a-z]{24}}/saveChildren/")
    public GeneralResponse<Boolean> updateSaveEmbedDemo(@ModelAttribute("token") String token
            , @PathVariable String id
            , @RequestBody List<DemoChildrenDto> demoChildrenList) {
        checkUser(token);
        demoService.updateSaveEmbedDemo(id, demoChildrenList);
        return GeneralResponse.success(true);

    }

    /**
     * 对指定demo删除嵌套数据
     *
     * @param token
     * @param id
     * @param demoChildrenIds 嵌套的id集
     * @return
     */
    @PatchMapping("/id/{id:[0-9a-z]{24}}/deleteChildren/")
    public GeneralResponse<Boolean> updateDeleteEmbedDemo(@ModelAttribute("token") String token
            , @PathVariable String id
            , @RequestBody List<String> demoChildrenIds) {
        checkUser(token);
        demoService.updateDeleteEmbedDemo(id, demoChildrenIds);
        return GeneralResponse.success(true);
    }

    /**
     * 获取指定demo详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:[0-9a-z]{24}}/")
    public GeneralResponse<DemoDetailsVo> findDemoById(@ModelAttribute("token") String token
            ,@PathVariable String id) {
        //checkUser(token);
        return GeneralResponse.success(demoService.findDemoById(id));
    }

    /**
     * demo不分页
     *
     * @return
     */
    @GetMapping("/")
    public GeneralResponse<List<DemoVo>> list() {
        return GeneralResponse.success(demoService.list());
    }

    /**
     * demo分页
     *
     * @param pageable 分页参数
     * @return
     */
    @GetMapping("/page/")
    public GeneralFlatPagedResponse<Demo> page(@ModelAttribute("page") Pageable pageable) {
        return GeneralFlatPagedResponse.build(demoService.page(pageable));
    }

}
