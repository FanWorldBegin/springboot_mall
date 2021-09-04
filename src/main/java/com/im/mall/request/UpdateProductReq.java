package com.im.mall.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 更新商品 请求参数类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductReq {

    @NotNull
    private Integer id;

    private String name;
    private String image;
    private String detail;
    private Integer categoryId;
    @Min(value = 1, message = "价格不能小于1") // 最小一分钱
    private Integer price;
    @Max(value = 10000, message = "库存不能大于1000")
    private Integer stock;
    private Integer status;
}