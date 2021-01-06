package com.eaphone.jiankang.demo.admin.controller;

import com.eaphone.jiankang.admin.controller.v2.BaseAdminCrudRestController;
import com.eaphone.jiankang.admin.model.CurrentUser;
import com.eaphone.jiankang.demo.admin.repo.AdminDemoRepository;
import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.dto.DemoDto;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.core.vo.DemoVo;
import com.eaphone.smarthealth.exception.rest.BadRequestException;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 后台管理API
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseAdminCrudRestController<Demo, DemoVo, DemoDetailsVo, DemoDto, DemoDetailsVo, String>{

    @Getter
    @Autowired
    private AdminDemoRepository repo;

    @Getter
    @Autowired
    private DemoService service;

    /**
     * 新增前执行
     * @param req 请求过来的数据
     * @return
     */
    @Override
    protected DemoDto postCheck(DemoDto req) {
        if (StringUtils.isEmpty(req.getName())){
            throw new BadRequestException("名称不能为空");
        }
        if (StringUtils.isEmpty(req.getPrice())){
            throw new BadRequestException("价格不能为空");
        }
        return super.postCheck(req);
    }

    /**
     * 更新前执行
     * @param dataInDb  持久化数据
     * @param req       请求过来的数据
     * @return
     */
    @Override
    protected DemoDetailsVo patchCheck(Demo dataInDb, DemoDetailsVo req) {
        if (StringUtils.isEmpty(req.getName())){
            req.setName(dataInDb.getName());
        }
        if (req.getPrice()==null){
            req.setPrice(dataInDb.getPrice());
        }
        if (CollectionUtils.isEmpty(req.getChildren())){
            req.setChildren(dataInDb.getChildren());
        }
        req.setCreateTime(new Date());
        return req;
    }
}
