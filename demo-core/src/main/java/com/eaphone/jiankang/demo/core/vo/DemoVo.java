package com.eaphone.jiankang.demo.core.vo;

import lombok.Data;

import java.util.Date;

/**
 * demo 列表数据VO
 */
@Data
public class DemoVo {
    private String id;//id

    private String name;//名称

    private Float price;//价格

    private Date createTime;//创建时间
}
