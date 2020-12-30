package com.eaphone.jiankang.demo.client.service.fallback;

import com.eaphone.smarthealth.client.model.ClientResponse;
import com.eaphone.jiankang.demo.client.dto.OrderResult;
import com.eaphone.jiankang.demo.client.service.DemoService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单失败回调
 */
@Component
public class DemoServiceFallBack implements DemoService {

    @Override
    public ClientResponse<List<OrderResult>> findAllByUserId(String userId) {
        return ClientResponse.fallback();
    }
    @Override
    public ClientResponse<Integer> findByOrderStatus(String orderNumber) {
        return ClientResponse.fallback();
    }
}
