package com.im.mall.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.dao.CategoryMapper;
import com.im.mall.model.pojo.Category;
import com.im.mall.model.vo.CategoryVO;
import com.im.mall.request.AddCategoryReq;
import com.im.mall.request.UpdateCategoryReq;
import com.im.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 *  目录分类的Service实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 添加前判断是否有重名的
     * @param addCategoryReq
     */

    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();

        // category.setName(addCategoryReq.getName());  一个个写入
        // 一次性把对象中同属性名的字段拷贝
        BeanUtils.copyProperties(addCategoryReq, category);

        // 查询是否有重名的
        Category CategoryOld = categoryMapper.selectByName(addCategoryReq.getName());

        if(CategoryOld != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw  new MallException(MallExceptionEnum.CREATE_FAILED);
        }

    }
    @Override
    public void update(UpdateCategoryReq updateCategoryReq) {

        Category updateCategory = new Category();

        // 一次性把对象中同属性名的字段拷贝
        BeanUtils.copyProperties(updateCategoryReq, updateCategory);

        if(updateCategoryReq.getName() !=null) {
            // mapper 返回的是 Category 类型
            Category categoryOld  = categoryMapper.selectByName(updateCategoryReq.getName());

            int old = categoryOld.getId();
            int newId = updateCategoryReq.getId();
            // 可以查到 且 库里传进来的id 和 和我传入的id 不一样 但是名字一样，说明冲突了
            if(categoryOld != null && !categoryOld.getId().equals(updateCategoryReq.getId())) {
                throw new MallException(MallExceptionEnum.NAME_EXISTED);
            }

            // 更新
            int count = categoryMapper.updateByPrimaryKeySelective(updateCategory);
            if (count == 0) {
                throw new MallException(MallExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        // 查询发现数据不存在
        if(categoryOld == null) {
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }

        int count = categoryMapper.deleteByPrimaryKey(id);

        if(count == 0) {
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }

    }

    // 需要分页
    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        // 排序 按照 type 第一优先
        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categoryList = categoryMapper.selectList();
        // 处理了当前页是多少，总共有多少页
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;
    }


    //  给前台用户展示
    @Override
    public List<CategoryVO> listCategoryForCustomer() {
        ArrayList<CategoryVO>  categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList, 0);

        return categoryVOList;

    }

    // 递归查找目录
    private void recursivelyFindCategories(List<CategoryVO> categoryVOList, Integer parentId) {
        // 1. 找父目录为parentId的所有值
        // 2. 递归获取所有子类别，并组合成为一个目录树
        List<Category> categoryList = categoryMapper.selectCategoriesByParentID(parentId);

        if(!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category =  categoryList.get(i);

                CategoryVO categoryVO = new CategoryVO();

                // 3. 用 category 作为 categoryVO 的源数据； 只多一个 childCategory
                BeanUtils.copyProperties(category, categoryVO);
                // 4. 添加到列表中
                categoryVOList.add(categoryVO);
                // 5.将 childCategory list 传入， 继续查它的子目录
                recursivelyFindCategories(categoryVO.getChildCategory(), categoryVO.getId());
            }
        }

    }
}
