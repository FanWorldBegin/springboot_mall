package com.im.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.im.mall.common.Constant;
import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.dao.ProductMapper;
import com.im.mall.model.pojo.Product;
import com.im.mall.model.query.ProductListQuery;
import com.im.mall.model.vo.CategoryVO;
import com.im.mall.request.AddProductReq;
import com.im.mall.request.ProductListReq;
import com.im.mall.request.UpdateProductReq;
import com.im.mall.service.CategoryService;
import com.im.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product(); // 实体类初始化
        // 将addProductReq 赋值给 product
        BeanUtils.copyProperties(addProductReq, product);

        Product productOld = productMapper.selectByName(addProductReq.getName());
        // 重名类
        if(productOld != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        int count =  productMapper.insertSelective(product);

        if(count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void update(Product updateProduct) {
        Product productOld = productMapper.selectByName(updateProduct.getName());
        // 同名且不同ID不能修改
        if(productOld != null && !productOld.getId().equals(updateProduct.getId())) {
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }

        int count = productMapper.updateByPrimaryKeySelective(updateProduct);

        if(count == 0) {
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }


    }


    @Override
    public void delete(Integer id) {
        Product productOld = productMapper.selectByPrimaryKey(id);
        // 查不到该记录无法删除
        if(productOld == null ) {
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }

        int count = productMapper.deleteByPrimaryKey(id);

        if(count == 0) {
            throw new MallException(MallExceptionEnum.DELETE_FAILED);
        }

    }
    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus) {
        productMapper.batchUpdateSellStatus(ids, sellStatus);

    }

    @Override
    public PageInfo listForAdmin(Integer pageNumber, Integer pageSize) {
        // 分页设置
        PageHelper.startPage(pageNumber, pageSize);
        List<Product> products = productMapper.selectListForAdmin();

        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;

    }

    /**
     * 前台商品详情
     */
    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    /**
     * 前台查询商品列表
     * @param productListReq
     * @return
     */
    @Override
    public PageInfo list(ProductListReq productListReq) {
        // 构建query对象
        ProductListQuery productListQuery = new ProductListQuery();

        // 搜索处理 - 不为空
        if(StringUtils.hasLength(productListReq.getKeyword())) {
            // 拼接字符串 - 用数据库的模糊查找功能
            String keyword = new StringBuilder().append("%").append(productListReq.getKeyword()).append("%").toString();
            productListQuery.setKeyword(keyword);
        }

        // 目录处理： 如果查某个目录下商品，不仅是查该目录下的，还需要把所有子目录商品查出来，所以要拿一个目录的id的list
        if(productListReq.getCategoryId() != null ) {
            // 得到的是一个树状结构
            List<CategoryVO> categoryVOList = categoryService.listCategoryForCustomer(productListReq.getCategoryId());
            ArrayList<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            // 获得商品树的所有id
            getCategoryIds(categoryVOList, categoryIds);
            productListQuery.setCategoryIds(categoryIds);
        }

        // 排序处理 - 前端请求传入
       String orderBy = productListReq.getOrderBy();

        //符合我们的约定
        if(Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize(), orderBy);
        } else {
            // 不符合规定，不排序
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize());
        }

        List<Product> productList = productMapper.selectList(productListQuery);

        PageInfo pageInfo = new PageInfo(productList);

        return  pageInfo;
    }

    // 递归遍历获得所有的id
    private void getCategoryIds(List<CategoryVO> categoryVOList, ArrayList<Integer> categoryIds) {
        // itli
        for (int i = 0; i < categoryVOList.size(); i++) {
            CategoryVO categoryVO =  categoryVOList.get(i);
            if(categoryVO != null) {
                categoryIds.add(categoryVO.getId());
                // 递归存储子节点id
                getCategoryIds(categoryVO.getChildCategory(), categoryIds);
            }
        }
    }
}
