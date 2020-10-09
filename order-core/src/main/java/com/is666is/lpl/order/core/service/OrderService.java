package com.is666is.lpl.order.core.service;

import com.alibaba.fastjson.JSON;
import com.eaphone.smarthealth.service.BaseMongoService;
import com.is666is.lpl.order.core.document.Order;
import com.is666is.lpl.order.core.repo.OrderRepo;
import com.is666is.lpl.order.core.util.query.OrderPageParam;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 订单业务接口实现类
 */
@Service
@Slf4j
public class OrderService extends BaseMongoService<Order> {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private OrderRepo orderRepo;

    /**
     * 获取指定用户的所有订单
     *
     * @param userId 用户id
     * @return 用户的订单
     */

    public List<Order> findAllByUserId(String userId) {
        return orderRepo.findAllByUserId(userId);
    }

    /**
     * 修改指定用户
     *
     * @param order 更新的订单
     * @return 是否更新成功
     */
    public boolean updateByUserIdAndOrderId(Order order) {
        int count = (int) mongoTemplate.updateFirst(
                new Query(Criteria.where("orderId").is(order.getOrderId())
                        .and("userId").is(order.getUserId())
                        .and("list.id")
                        .is(order.getList() != null && order.getList().size() > 0 ? order.getList().get(0).getId() : 0))
                , updateProperty(order, true)
                , Order.class).getModifiedCount();
        return count > 0;
    }

    /**
     * @param obj  新订单
     * @param bool 空值是否操作
     * @param <T>  新对象
     * @return 封装更新对象
     */
    private <T> Update updateProperty(T obj, boolean bool) {
        Update update = new Update();
        StringBuilder sb = new StringBuilder();
        //解析为document对象
        Document document = Document.parse(JSON.toJSONString(obj));
        for (Map.Entry<String, Object> entrySet : document.entrySet()) {
            //去除isDelete字段
            if ("isDelete".equals(entrySet.getKey()))
                continue;
            //判断是否有多的一方
            if (entrySet.getValue() instanceof List) {
                List<?> list = (List<?>) entrySet.getValue();
                if (list != null && list.size() > 0) {
                    //只需要获取第一个
                    Document builtDocument = Document.parse(JSON.toJSONString(list.get(0)));
                    for (Map.Entry<String, Object> builtEntrySet : builtDocument.entrySet()) {
                        //去除isDelete字段
                        if ("isDelete".equals(builtEntrySet.getKey()))
                            continue;
                        //判断多方空值是否更新
                        if (!(bool) && StringUtils.isEmpty(builtEntrySet.getValue()))
                            continue;
                        //对内嵌进行修改
                        update.set(String.format("%s.$.%s", entrySet.getKey(), builtEntrySet.getKey()),
                                builtEntrySet.getValue());
                        sb.append(String.format("%s.$.%s:%s ", entrySet.getKey(), builtEntrySet.getKey(),
                                builtEntrySet.getValue()));
                    }
                }
                continue;
            }
            //判断空值是否更新
            if (!(bool) && StringUtils.isEmpty(entrySet.getValue()))
                continue;
            update.set(entrySet.getKey(), entrySet.getValue());
            sb.append(String.format("%s:%s ", entrySet.getKey(), entrySet.getValue()));
        }
        log.info(sb.toString());
        return update;
    }

    /**
     * 分页订单
     *
     * @param orderPageParam 订单分页条件
     * @return 当前页数据
     */
    public Map<String, Object> getOrderPage(OrderPageParam orderPageParam) {
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
            query.addCriteria(Criteria.where("orderNumber").regex(compile));
        }
        //发货时间段
        Date startDate = null, endDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            startDate = format.parse("1970-01-01 12:22:11");//默认最小发货时间段
            endDate = format.parse("2100-01-01 12:22:11");//默认最大发货时间段
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.addCriteria(Criteria.where("shipTime")
                .gte(orderPageParam.getMinShipTime() != null ? orderPageParam.getMinShipTime() : startDate)
                .lte(orderPageParam.getMaxShipTime() != null ? orderPageParam.getMaxShipTime() : endDate));
        if (orderPageParam.getStatus() != null && orderPageParam.getStatus() > -1)
            query.addCriteria(Criteria.where("status").is(orderPageParam.getStatus()));
        //总数据条
        int totalCount = (int) mongoTemplate.count(query, Order.class);
        //分页
        query.skip((orderPageParam.getCurrentPage() - 1) * orderPageParam.getPageSize())
                .limit(orderPageParam.getPageSize());
        //总页数
        int totalPage = (int) Math.ceil(totalCount * 1.0 / orderPageParam.getPageSize());
        orderPageResult.put("totalCount", totalCount);
        orderPageResult.put("totalPage", totalPage);
        //根据下单时间倒序排序
        query.with(Sort.by(Sort.Order.desc("shipTime")));
        List<Order> orders = mongoTemplate.find(query, Order.class);
        orderPageResult.put("orders", orders);//结果集
        return orderPageResult;
    }

    /**
     * 根据指定订单编号获取指定订单
     *
     * @param orderNumber 订单编号
     * @return 订单状态 -1不存在 0待付款  1待发货  2已发货  3已收货  4已完成
     */
    public Integer findByOrderStatus(String orderNumber) {
        Order order = orderRepo.findFirstByOrderNumber(orderNumber);
        if (order == null)
            return -1;
        return order.getStatus();
    }

}
