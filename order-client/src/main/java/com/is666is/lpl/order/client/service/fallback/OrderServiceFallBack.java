package com.is666is.lpl.order.client.service.fallback;

import com.eaphone.smarthealth.client.model.ClientResponse;
import com.is666is.lpl.order.client.dto.OrderResult;
import com.is666is.lpl.order.client.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单失败回调
 */
@Component
public class OrderServiceFallBack implements OrderService {

    @Override
    public ClientResponse<List<OrderResult>> findAllByUserId(String userId) {
        return ClientResponse.fallback();
    }
    @Override
    public ClientResponse<Integer> findByOrderStatus(String orderNumber) {
        return ClientResponse.fallback();
    }
}
