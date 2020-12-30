package com.eaphone.jiankang.demo.admin.controller;

import com.eaphone.jiankang.admin.controller.v2.BaseAdminCrudRestController;
import com.eaphone.jiankang.demo.admin.repo.AdminDemoRepository;
import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.service.DemoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理API
 */
@RestController
@RequestMapping("/order")
public class DemoController extends BaseAdminCrudRestController<Demo, Demo, Demo, Demo, Demo, String> {

    @Getter
    @Autowired
    private AdminDemoRepository repo;

    @Getter
    @Autowired
    private DemoService service;

    //    BaseAdminDataTablesController BaseAdminCrudRestController

    //

    @Override protected Demo beforeSave(Demo dataToSave) {
        return super.beforeSave(dataToSave);
    }
}
