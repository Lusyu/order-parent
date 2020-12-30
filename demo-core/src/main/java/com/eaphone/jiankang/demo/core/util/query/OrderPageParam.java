package com.eaphone.jiankang.demo.core.util.query;

import com.eaphone.jiankang.demo.core.util.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class OrderPageParam extends PageParam {
    private Float minPayPrice;//最小价格
    private Float maxPayPrice;//最大价格
    private String orderNumber;//订单编号
    private Integer status;//订单状态

}
