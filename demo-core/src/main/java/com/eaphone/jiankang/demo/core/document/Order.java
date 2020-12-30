package com.eaphone.jiankang.demo.core.document;

import com.eaphone.jiankang.demo.core.document.embed.EmbeddedProduct;
import com.eaphone.smarthealth.jsonview.View;
import com.eaphone.smarthealth.model.BaseDocument;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单实体
 */
@Data
@Document(collection = "pms_order")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@CompoundIndex(name = "idx_order_id", def = "{orderId:-1}")
public class Order extends BaseDocument implements Serializable {

    public static final String ORDER_ID = "orderId";
    public static final String USER_ID="userId";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String STATUS = "status";
    public static final String RECEIPT_ADDRESS="receiptAddress";

    /**
     * 订单id
     */
    @Indexed
    @JsonView(View.class)
    private String orderId;
    /**
     * 订单编号
     */
    @JsonView(View.class)
    private String orderNumber;

    /**
     * 用户的id
     */
    @Indexed
    @JsonView(View.class)
    private String userId;
    /**
     * 支付价格
     */
    @JsonView(View.class)
    private Float payPrice;
    /**
     * 发货时间
     */
    @JsonView(View.class)
    private Date shipTime;
    /**
     * 收货地址
     */
    @JsonView(View.class)
    private String receiptAddress;
    /**
     * 订单状态
     */
    @JsonView(View.class)
    private Integer status;
    /**
     * 购买的商品
     */
    @JsonView(View.class)
    private List<EmbeddedProduct> list;
}
