package com.eaphone.jiankang.demo.core.vo;

import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import lombok.Data;

import java.util.List;

/**
 * demo详情Vo
 */
@Data
public class DemoDetailsVo extends DemoVo {

    private List<EmbeddedDemo> children;//嵌套的子文档数据
}
