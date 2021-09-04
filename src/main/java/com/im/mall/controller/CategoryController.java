package com.im.mall.controller;

import com.github.pagehelper.PageInfo;
import com.im.mall.common.ApiRestResponse;
import com.im.mall.common.Constant;
import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.pojo.User;
import com.im.mall.model.vo.CategoryVO;
import com.im.mall.request.AddCategoryReq;
import com.im.mall.request.UpdateCategoryReq;
import com.im.mall.service.CategoryService;
import com.im.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 目录Controller
 */
@Controller
public class CategoryController {
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;
    /**
     * 后台添加目录
     * @RequestBody - 从请求的body 中获取
     * @param session 需要登录且是管理员
     * @return
     */
    @ApiOperation("后台添加目录")
    @PostMapping("admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) {

        //判断用户是否登录
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if(currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        // 校验是否是管理员
        boolean adminRole = userService.checkAdinRole(currentUser);

        if(adminRole) {
            // 执行操作
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();

        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    @ApiOperation("后台更新目录")
    @PostMapping("admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(HttpSession session, @Valid @RequestBody UpdateCategoryReq updateCategoryReq) {
        //判断用户是否登录
        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if(currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        // 校验是否是管理员
        boolean adminRole = userService.checkAdinRole(currentUser);

        if(adminRole) {
            // 执行操作
            categoryService.update(updateCategoryReq);
            return ApiRestResponse.success();

        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }

    }

    // 全局设置了过滤器进行管理员全线校验
    @ApiOperation("后台删除目录")
    @PostMapping("admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台目录列表")
    @PostMapping("admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);

    }



    @ApiOperation("前台目录列表")
    @PostMapping("category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForCustomer() {

        List<CategoryVO> categoryVOS = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOS);

    }
}
