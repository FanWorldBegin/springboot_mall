package com.im.mall.controller;

import com.im.mall.common.ApiRestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 购物车controller
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        // 用户登录后才能登录

        return null;

    }
}
