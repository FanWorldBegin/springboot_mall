package com.im.mall.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询商品列表的query
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListQuery {

    private  String keyword;
    // 把列表目录的所有id都传入
    private List<Integer> categoryIds;
}
