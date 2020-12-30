package com.eaphone.jiankang.demo.core.service;

import com.eaphone.jiankang.demo.core.document.Demo;

import com.eaphone.jiankang.demo.core.redis.UserDemoKeys;
import com.eaphone.jiankang.demo.core.repo.DemoRepo;
import com.eaphone.jiankang.demo.core.util.query.DemoPageParam;
import com.eaphone.smarthealth.redis.entity.BaseRedisModel;
import com.eaphone.smarthealth.redis.service.RedisService;
import com.eaphone.smarthealth.service.BaseMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 订单业务接口实现类
 */
@Service
@Slf4j
public class DemoService extends BaseMongoService<Demo> {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private DemoRepo demoRepo;
    @Autowired
    private RedisService redisService;

    /**
     * 获取指定用户的所有订单
     *
     * @param userId 用户id
     * @return 用户的订单
     */
    public List<Demo> findAllByUserId(String userId) {
        //查询缓存
        BaseRedisModel<List<Demo>> redisModel = redisService.get(UserDemoKeys.USER_DEMO_KEYS, userId, List.class);
        if (redisModel == null || redisModel.getData() == null) {
            System.out.println("查询数据库........................");
            //查看数据库 缓存该用户订单
            List<Demo> userDemos = demoRepo.findAllByUserId(userId);
            if (userDemos != null) {
                //默认过期3分钟  单位S  持久化需设置负整数
                redisService.set(UserDemoKeys.USER_DEMO_KEYS, userId, new BaseRedisModel(userDemos));
                return userDemos;
            } else {
                return Collections.emptyList();
            }
        }
        return redisModel.getData();
    }

    /**
     * 修改指定demo地址
     *
     * @param demoId
     * @param address 新地址
     * @return
     */
    public boolean updateDemoAddress(String demoId,String address) {
        int count = (int) mongoTemplate.updateFirst(
                new Query(new Criteria().where(Demo.DEMO_ID).is(demoId)
                        .orOperator(//订单状态必须是待付款或待发货
                                Criteria.where(Demo.STATUS).is(0),
                                Criteria.where(Demo.STATUS).is(1)))
                , Update.update(Demo.RECEIPT_ADDRESS, address)
                , Demo.class).getModifiedCount();
        return count > 0;
    }

    /**
     * demo订单
     *
     * @param demoPageParam demo分页条件
     * @return              当前页数据
     */
    public Map<String, Object> getDemoPage(DemoPageParam demoPageParam) {

        Map<String, Object> orderPageResult = new HashMap<String, Object>() {{
            put("currentPage", demoPageParam.getCurrentPage());
            put("pageSize", demoPageParam.getPageSize());
        }};

        Query query = new Query();
        //价格
        query.addCriteria(Criteria.where("payPrice")
                .gte(demoPageParam.getMinPayPrice() != null && demoPageParam.getMinPayPrice() > 0 ?
                        demoPageParam.getMinPayPrice() : 0)
                .lte(demoPageParam.getMaxPayPrice() != null && demoPageParam.getMaxPayPrice() < 999999999999999999L
                        ? demoPageParam.getMaxPayPrice()
                        : 999999999999999999L));
        //模糊查demoId
        if (!StringUtils.isEmpty(demoPageParam.getDemoId())) {
            Pattern compile = Pattern//模糊查
                    .compile("^.*" + demoPageParam.getDemoId() + ".*$", Pattern.CASE_INSENSITIVE);//模糊查
            query.addCriteria(Criteria.where("demoId").regex(compile));
        }
        if (demoPageParam.getStatus() != null && demoPageParam.getStatus() > -1)
            query.addCriteria(Criteria.where("status").is(demoPageParam.getStatus()));
        //总数据条
        int totalCount = (int) mongoTemplate.count(query, Demo.class);
        //分页
        query.skip((demoPageParam.getCurrentPage() - 1) * demoPageParam.getPageSize())
                .limit(demoPageParam.getPageSize());
        //总页数
        int totalPage = (int) Math.ceil(totalCount * 1.0 / demoPageParam.getPageSize());
        orderPageResult.put("totalCount", totalCount);
        orderPageResult.put("totalPage", totalPage);
        //根据时间倒序排序
        query.with(Sort.by(Sort.Order.desc("shipTime")));
        List<Demo> demos = mongoTemplate.find(query, Demo.class);
        orderPageResult.put("demos", demos);//结果集
        return orderPageResult;
    }

    /**
     * 分页
     *
     * @param pageable 分页参数
     * @return
     */
    public Page<Demo> pageTwo(Pageable pageable) {
        //query条件  pageable页参数
        return findPage(new Query(), pageable);
    }

    /**
     * 根据指定demoId获取指定demo状态
     *
     * @param demoId
     * @return demo状态  0不存在 1待付款  2待发货  3已发货  4待收货  5已收货  6已完成
     */
    public Integer findDemoStatusByDemoId(String demoId) {
        Demo demo = demoRepo.findFirstByDemoId(demoId);
        return demo == null ? 0 : demo.getStatus();
    }

    /**
     * demo状态修改
     *
     * @param demoId
     * @param status  需修改的订单状态
     * @return
     */
    public boolean updateStatus(String demoId, Integer status) {
        return mongoTemplate
                .updateFirst(Query.query(Criteria.where(Demo.DEMO_ID).is(demoId))
                        , Update.update(Demo.STATUS, status)
                        , Demo.class).getModifiedCount() > 0;
    }


    /**
     * demo更新
     *
     * @param demo
     * @return
     */
    public boolean updateDemo(Demo demo) {
        if (demo != null && demo.getId() != null) {
            return false;
        }
        return demoRepo.existsById(demo.getId())
                ? save(demo) != null
                : false;
    }
}
