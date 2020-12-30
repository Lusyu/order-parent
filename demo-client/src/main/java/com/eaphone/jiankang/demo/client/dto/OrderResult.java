package com.eaphone.jiankang.demo.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单实体
 */
@Data
public class OrderResult {
    /**
     * 订单id
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 用户的id
     */
    private String userId;
    /**
     * 支付价格
     */
    private BigDecimal payPrice;
    /**
     * 发货时间
     */
    private Date shipTime;
    /**
     * 收货地址
     */
    private String receiptAddress;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 购买的商品
     */
    private List<ProductResult> list;
}
