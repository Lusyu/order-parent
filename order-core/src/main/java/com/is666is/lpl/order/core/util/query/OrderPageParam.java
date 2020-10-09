package com.is666is.lpl.order.core.util.query;

import com.is666is.lpl.order.core.util.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class OrderPageParam extends PageParam {
    private Float minPayPrice;//最小价格
    private Float maxPayPrice;//最大价格
    private Date minShipTime;//起始发货最小时间
    private Date maxShipTime;//起始发货最大时间
    private String orderNumber;//订单编号
    private Integer status;//订单状态

}
