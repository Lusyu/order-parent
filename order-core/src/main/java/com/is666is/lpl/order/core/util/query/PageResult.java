package com.is666is.lpl.order.core.util.query;

import lombok.Data;

@Data
public class PageResult {
    private Integer currentPage=1;
    private Integer pageSize=4;
    private Integer totalCount=1;
    private Integer totalPage=1;
}
