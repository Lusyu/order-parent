package com.is666is.lpl.order.core.document;

import com.eaphone.smarthealth.model.BaseDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单实体
 */
@Data
@Document(collection = "pms_order")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseDocument implements Serializable {
    /**
     * 订单id
     */
    @Indexed
    private String orderId;
    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 用户的id
     */
    @Indexed
    private String userId;
    /**
     * 支付价格
     */
    private Float payPrice;
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
    private List<Product> list;
}
