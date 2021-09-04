package com.im.mall.service;

import com.github.pagehelper.PageInfo;
import com.im.mall.model.pojo.Product;
import com.im.mall.model.vo.CategoryVO;
import com.im.mall.request.AddCategoryReq;
import com.im.mall.request.AddProductReq;
import com.im.mall.request.ProductListReq;
import com.im.mall.request.UpdateCategoryReq;

import java.util.List;

/**
 * 商品的Service接口
 */

public interface ProductService {


    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNumber, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);
}
