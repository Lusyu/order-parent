package com.eaphone.jiankang.demo.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class DemoResult {

    /**
     * DemoId
     */
    private String id;
    /**
     * 价格
     */
    private float payPrice;
    /**
     * 时间
     */
    private Date shipTime;
    /**
     * 地址
     */
    private String receiptAddress;
    /**
     * 状态
     */
    private Integer status;
}
