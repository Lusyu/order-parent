package com.eaphone.jiankang.demo.client.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class DemoVo {

    private String id;//id

    private String name;//名称

    private float price;//价格

    private Date createTime;//创建时间

}
