package com.eaphone.jiankang.demo.client.service.fallback;

import com.eaphone.smarthealth.client.model.ClientResponse;
import com.eaphone.jiankang.demo.client.dto.DemoResult;
import com.eaphone.jiankang.demo.client.service.DemoService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * demo失败回调
 */
@Component
public class DemoServiceFallBack implements DemoService {

    @Override
    public ClientResponse<List<DemoResult>> findUserDemos(String userId) {
        return ClientResponse.fallback();
    }
    @Override
    public ClientResponse<Integer> findDemoStatus(String orderNumber) {
        return ClientResponse.fallback();
    }
}
