package com.eaphone.jiankang.demo.core.repo;

import com.eaphone.jiankang.demo.core.document.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单Repository
 */
@Repository
public interface DemoRepo extends MongoRepository<Demo, String> {

    /**
     * 获取用户所有订单
     *
     * @param userId 用户id
     * @return 用户的所有订单
     */
    List<Demo> findAllByUserId(String userId);

    /**
     * 根据指定订单编号获取指定订单
     *
     * @param orderId 订单编号
     * @return 订单
     */
    Demo findFirstByOrderId(String orderId);

}
