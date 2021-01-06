package com.eaphone.jiankang.demo.core.repo;

import com.eaphone.jiankang.demo.core.document.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * 订单Repository
 */
@Repository
public interface DemoRepo extends MongoRepository<Demo, String> {


}
