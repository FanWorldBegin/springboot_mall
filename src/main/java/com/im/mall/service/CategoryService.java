package com.im.mall.service;

import com.github.pagehelper.PageInfo;
import com.im.mall.model.vo.CategoryVO;
import com.im.mall.request.AddCategoryReq;
import com.im.mall.request.UpdateCategoryReq;

import java.util.List;

/**
 * 目录分类的Service接口
 */

public interface CategoryService {


    void add(AddCategoryReq addCategoryReq);

    void update(UpdateCategoryReq updateCategoryReq);

    void delete(Integer id);

    // 需要分页
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
