package com.is666is.lpl.order.core.document;

import com.eaphone.smarthealth.model.BaseDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实体
 */
@Data
@Document(collection = "pms_product")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseDocument implements Serializable {
    /**
     * 商品id
     */
    @Indexed
    private String id;
    /**
     * 商品昵称
     */
    private String name;
    /**
     * 商品单价
     */
    private Float price;
}
