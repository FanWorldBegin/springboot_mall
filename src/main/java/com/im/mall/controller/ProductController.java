package com.im.mall.controller;

import com.github.pagehelper.PageInfo;
import com.im.mall.common.ApiRestResponse;
import com.im.mall.model.pojo.Product;
import com.im.mall.request.ProductListReq;
import com.im.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台商品Controller
 */
@RestController  // 自动加RestBody
public class ProductController {

    // controller 区分前后台，service不需要，又可以复用的方法
    @Autowired
    ProductService productService;

    /**
     * 商品详情方法
     */
    @ApiOperation("商品详情")
    @GetMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {

        Product detail = productService.detail(id);
        return ApiRestResponse.success(detail);
    }

    @ApiOperation("商品查询列表")
    @GetMapping("product/list")
    public ApiRestResponse list(ProductListReq productListReq) {

        PageInfo pageInfo = productService.list(productListReq);
        return ApiRestResponse.success(pageInfo);
    }
}
