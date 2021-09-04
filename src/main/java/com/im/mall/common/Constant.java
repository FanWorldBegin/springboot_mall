package com.im.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *  存放常量
 */

@Component  // 通知spring 注入value
public class Constant {
    public static final String SALT = "kdfhdk98298392TTTSDhs>>>>...";
    public static final String MALL_USER = "MALL_USER";


    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }


    public interface ProductListOrderBy {
        Set<String>  PRICE_ASC_DESC = Sets.newHashSet("price desc", "price asc");
    }
}
