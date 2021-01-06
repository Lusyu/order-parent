package com.eaphone.jiankang.demo.client.service.fallback;

import com.eaphone.jiankang.demo.client.dto.DemoDto;
import com.eaphone.jiankang.demo.client.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.client.vo.DemoVo;
import com.eaphone.smarthealth.client.model.ClientResponse;
import com.eaphone.jiankang.demo.client.service.DemoService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * demo失败回调
 */
@Component
public class DemoServiceFallBack implements DemoService {
    @Override public ClientResponse<Boolean> saveDemo(DemoDto demoDto) {
        return ClientResponse.fallback();
    }

    @Override public ClientResponse<Boolean> deleteDemoById(String id) {
        return ClientResponse.fallback();
    }

    @Override public ClientResponse<Boolean> updateDemoById(String id, String name, Float price) {
        return ClientResponse.fallback();
    }

    @Override public ClientResponse<DemoDetailsVo> findDemoById(String id) {
        return ClientResponse.fallback();
    }

    @Override public ClientResponse<List<DemoVo>> findDemos() {
        return ClientResponse.fallback();
    }
}
