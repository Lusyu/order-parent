package com.eaphone.jiankang.demo.core.document.embed;

import com.eaphone.jiankang.demo.core.dto.DemoChildrenDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * 嵌套demo
 */
@Data
public class EmbeddedDemo {

    @Field("id")
    private String id;//id

    private String name;//名称

    private Float price;//价格

    /**
     * 新增 语法糖
     * @param demoChildrenDto
     * @return
     */
    public static EmbeddedDemo build(DemoChildrenDto demoChildrenDto){
        EmbeddedDemo embeddedDemo = new EmbeddedDemo();
        BeanUtils.copyProperties(demoChildrenDto,embeddedDemo);
        return embeddedDemo;
    }
}
