package com.is666is.lpl.order.client.dto;

import lombok.Data;


import java.math.BigDecimal;

/**
 * 商品实体
 */
@Data
public class ProductResult {
    /**
     *商品id
     */
    private String id;
    /**
     *商品昵称
     */
    private String name;
    /**
     *商品单价
     */
    private BigDecimal price;
}
