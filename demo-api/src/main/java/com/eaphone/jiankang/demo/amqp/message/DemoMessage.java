package com.eaphone.jiankang.demo.amqp.message;

import lombok.Data;

@Data
public class DemoMessage {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private Float price;
}
