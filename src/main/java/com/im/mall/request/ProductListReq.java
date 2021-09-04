package com.im.mall.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 前台商品列表 参数类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListReq {

    private String keyword;

    private Integer categoryId;

    private String orderBy;

    private Integer pageNum = 1;
    private Integer pageSize = 1;
}
