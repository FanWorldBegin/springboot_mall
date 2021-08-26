package com.im.mall.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 添加目录的请求类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryReq {
    @Size(min = 2, max = 5) // 目录长度 2 - 5 个字
    @NotNull
    private String name;

    @Max(3) //数量最大值 限制最多3层
    @NotNull
    private Integer type;

    @NotNull(message = "parentId 不能为null")
    private Integer parentId;

    @NotNull
    private Integer orderNum;
}
