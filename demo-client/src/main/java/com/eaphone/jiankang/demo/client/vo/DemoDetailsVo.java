package com.eaphone.jiankang.demo.client.vo;

import lombok.Data;

import java.util.List;

@Data
public class DemoDetailsVo extends DemoVo {
    private List<EmbedDemoVo> children;
}
