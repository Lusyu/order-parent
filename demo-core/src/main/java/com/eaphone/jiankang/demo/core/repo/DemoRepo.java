package com.eaphone.jiankang.demo.core.repo;

import com.eaphone.jiankang.demo.core.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单Repository
 */
@Repository
public interface DemoRepo extends MongoRepository<Order, String> {

    /**
     * 获取用户所有订单
     *
     * @param userId 用户id
     * @return 用户的所有订单
     */
    List<Order> findAllByUserId(String userId);

    /**
     * 根据指定订单编号获取指定订单
     *
     * @param orderNumber 订单编号
     * @return 订单
     */
    Order findFirstByOrderNumber(String orderNumber);

}
