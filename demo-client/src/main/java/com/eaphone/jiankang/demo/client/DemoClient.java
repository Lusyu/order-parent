package com.eaphone.jiankang.demo.client;

import com.eaphone.jiankang.demo.client.dto.DemoDto;
import com.eaphone.jiankang.demo.client.service.DemoService;
import com.eaphone.jiankang.demo.client.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.client.vo.DemoVo;
import com.eaphone.smarthealth.client.model.ClientResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Demo Client
 */
@Slf4j
public class DemoClient {
    @Autowired
    private DemoService demoService;

    /**
     * 新增demo
     *
     * @param demoDto
     * @return
     */
    public Boolean saveDemo(@NonNull DemoDto demoDto) {
        try {
            ClientResponse<Boolean> response = demoService.saveDemo(demoDto);
            return response.getData() != null && response.isSuccess();
        } catch (Exception e) {
            log.error("saveDemo", e.getMessage());
        }
        return false;
    }

    /**
     * 删除demo
     *
     * @param id
     * @return
     */
    public Boolean deleteDemoById(@NonNull String id) {

        try {
            ClientResponse<Boolean> response = demoService.deleteDemoById(id);
            return response.getData() != null && response.isSuccess();
        } catch (Exception e) {
            log.error("deleteDemoById--", e.getMessage());
        }
        return false;
    }

    /**
     * 根据id更新demo的名称和价格
     *
     * @param id
     * @param name  名称
     * @param price 价格
     * @return
     */
    public Boolean updateDemoById(@NonNull String id
            , @NonNull String name
            , @NonNull Float price) {
        try {
            ClientResponse<Boolean> response = demoService.updateDemoById(id, name, price);
            return response.getData() != null && response.isSuccess();
        } catch (Exception e) {
            log.error("updateDemo", e.getMessage());
        }
        return false;
    }

    /**
     * 根据指定id获取demo详情
     *
     * @param id
     * @return
     */
    public DemoDetailsVo findDemoById(@NonNull String id) {
        try {
            ClientResponse<DemoDetailsVo> response = demoService.findDemoById(id);
            DemoDetailsVo data = response.getData();
            return data != null && response.isSuccess() ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有demo
     * @return
     */
    public List<DemoVo> findDemos(){
        List<DemoVo> list= Collections.emptyList();
        try {
            ClientResponse<List<DemoVo>> response = demoService.findDemos();
            List<DemoVo> data = response.getData();
            return !CollectionUtils.isEmpty(data) &&response.isSuccess()?data:list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
