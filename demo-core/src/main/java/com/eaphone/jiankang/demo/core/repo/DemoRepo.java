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
     * 获取指定用户所有demo
     *
     * @param userId
     * @return
     */
    List<Demo> findAllByUserId(String userId);

    /**
     * 根据指定demoId获取指定demo
     *
     * @param demoId
     * @return
     */
    Demo findFirstByDemoId(String demoId);

}
