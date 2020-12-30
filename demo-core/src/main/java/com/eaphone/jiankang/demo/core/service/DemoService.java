package com.eaphone.jiankang.demo.core.service;

import com.eaphone.jiankang.demo.core.document.Demo;

import com.eaphone.jiankang.demo.core.redis.UserOrderKeys;
import com.eaphone.jiankang.demo.core.repo.DemoRepo;
import com.eaphone.smarthealth.redis.entity.BaseRedisModel;
import com.eaphone.smarthealth.redis.service.RedisService;
import com.eaphone.smarthealth.service.BaseMongoService;
import com.eaphone.jiankang.demo.core.util.query.OrderPageParam;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        BaseRedisModel<List<Demo>> redisModel = redisService.get(UserOrderKeys.USER_ORDER_KEYS, userId, List.class);
        if (redisModel == null || redisModel.getData() == null) {
            System.out.println("查询数据库........................");
            //查看数据库 缓存该用户订单
            List<Demo> userDemos = demoRepo.findAllByUserId(userId);
            if (userDemos != null) {
                //默认过期3分钟  单位S  持久化需设置负整数
                redisService.set(UserOrderKeys.USER_ORDER_KEYS, userId, new BaseRedisModel(userDemos));
                return userDemos;
            } else {
                return Collections.emptyList();
            }
        }
        return redisModel.getData();
    }

    /**
     * 修改订单收货地址
     *
     * @param demo 更新的订单收货地址
     * @return 是否更新成功
     */
    public boolean updateByUserIdAndOrderId(Demo demo) {
        int count = (int) mongoTemplate.updateFirst(
                new Query(new Criteria().where(Demo.ORDER_ID).is(demo.getOrderId())
                        .orOperator(//订单状态必须是待付款或待发货
                                Criteria.where(Demo.STATUS).is(0),
                                Criteria.where(Demo.STATUS).is(1))
                )
                , Update.update(Demo.RECEIPT_ADDRESS, demo.getReceiptAddress())
                , Demo.class).getModifiedCount();

        return count > 0;
    }

    /**
     * 分页订单
     *
     * @param orderPageParam 订单分页条件
     * @return 当前页数据
     */
    public Map<String, Object> getOrderPage(OrderPageParam orderPageParam) {//Pageable pageable
        //this.findPage(new Query(), orderPageParam);

        Map<String, Object> orderPageResult = new HashMap<String, Object>() {{
            put("currentPage", orderPageParam.getCurrentPage());
            put("pageSize", orderPageParam.getPageSize());
        }};

        Query query = new Query();
        //订单价格
        query.addCriteria(Criteria.where("payPrice")
                .gte(orderPageParam.getMinPayPrice() != null && orderPageParam.getMinPayPrice() > 0 ?
                        orderPageParam.getMinPayPrice() : 0)
                .lte(orderPageParam.getMaxPayPrice() != null && orderPageParam.getMaxPayPrice() < 999999999999999999L
                        ? orderPageParam.getMaxPayPrice()
                        : 999999999999999999L));
        //订单号
        if (!StringUtils.isEmpty(orderPageParam.getOrderNumber())) {
            Pattern compile = Pattern//模糊查
                    .compile("^.*" + orderPageParam.getOrderNumber() + ".*$", Pattern.CASE_INSENSITIVE);//模糊查
            query.addCriteria(Criteria.where("orderId").regex(compile));
        }
        if (orderPageParam.getStatus() != null && orderPageParam.getStatus() > -1)
            query.addCriteria(Criteria.where("status").is(orderPageParam.getStatus()));
        //总数据条
        int totalCount = (int) mongoTemplate.count(query, Demo.class);
        //分页
        query.skip((orderPageParam.getCurrentPage() - 1) * orderPageParam.getPageSize())
                .limit(orderPageParam.getPageSize());
        //总页数
        int totalPage = (int) Math.ceil(totalCount * 1.0 / orderPageParam.getPageSize());
        orderPageResult.put("totalCount", totalCount);
        orderPageResult.put("totalPage", totalPage);
        //根据下单时间倒序排序
        query.with(Sort.by(Sort.Order.desc("shipTime")));
        List<Demo> demos = mongoTemplate.find(query, Demo.class);
        orderPageResult.put("orders", demos);//结果集
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
     * 根据指定订单编号获取指定订单状态
     *
     * @param orderId 订单编号
     * @return 订单状态 -1不存在 0待付款  1待发货  2已发货  3待收货  4已收货  5已完成
     */
    public Integer findFirstByOrderId(String orderId) {
        Demo demo = demoRepo.findFirstByOrderId(orderId);
        return demo == null ? -1 : demo.getStatus();
    }

    /**
     * 订单状态修改
     *
     * @param orderId 订单id
     * @param status  需修改的订单状态
     * @return
     */
    public boolean updateStatus(String orderId, Integer status) {
        return mongoTemplate
                .updateFirst(Query.query(Criteria.where(Demo.ORDER_ID).is(orderId))
                        , Update.update(Demo.STATUS, status)
                        , Demo.class).getModifiedCount() > 0;
    }

    /**
     * 后台新增订单
     *
     * @return 是否保存成功
     */
    public boolean saveOrder(Demo demo) {
        return mongoTemplate.save(demo) != null;
    }

    /**
     * 后台订单的修改
     *
     * @param demo
     * @return
     */
    public boolean updateOrder(Demo demo) {
        if (demo != null && demo.getId() != null) {
            return false;
        }
        return demoRepo.existsById(demo.getId())
                ? save(demo) != null
                : false;
    }
}
