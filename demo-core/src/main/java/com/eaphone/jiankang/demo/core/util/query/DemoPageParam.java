package com.eaphone.jiankang.demo.core.util.query;

import com.eaphone.jiankang.demo.core.util.PageParam;
import lombok.Data;

import java.util.Date;

@Data
public class DemoPageParam extends PageParam {
    private String demoId;
    private Float minPayPrice;//最小价格
    private Float maxPayPrice;//最大价格
    private Integer status;//订单状态

}
