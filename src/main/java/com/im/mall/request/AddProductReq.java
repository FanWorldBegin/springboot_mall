package com.im.mall.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 请求参数类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductReq {

    @NotNull(message="商品名称不能为null")
    private String name;

    @NotNull(message="商品图片不能为null")
    private String image;
    private String detail;

    @NotNull(message="商品分类不能为null")
    private Integer categoryId;

    @NotNull(message="商品价格不能为null")
    @Min(value = 1, message = "价格不能小于1") // 最小一分钱
    private Integer price;

    @NotNull(message="商品分类不能为null")
    @Max(value = 10000, message = "库存不能大于1000")
    private Integer stock;
    private Integer status;
}