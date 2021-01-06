package com.eaphone.jiankang.demo.core.dto;

import lombok.Data;

/**
 * 增加与更新子文档数据Dto
 */
@Data
public class DemoChildrenDto extends DemoDto {

     private String embedId;//嵌套子文档 id
}
