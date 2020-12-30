package com.eaphone.jiankang.demo.core.document.embed;

import com.eaphone.smarthealth.jsonview.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * 商品实体
 */
@Data
public class EmbeddedProduct {
    /**
     * 商品id
     */
    @Field("id")
    @Indexed
    @JsonView(View.class)
    private String id;
    /**
     * 商品昵称
     */
    @JsonView(View.class)
    private String name;
    /**
     * 商品单价
     */
    @JsonView(View.class)
    private Float price;
}
