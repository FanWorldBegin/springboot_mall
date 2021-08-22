package com.im.mall.util;

import com.im.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 是哈希算法
 * 会把字符串通过哈希方式转换为其他字符串
 * 是无法反推的
 */
public class MD5Utils {
    // 工具类用 static 修饰方便调用

    // 字符串转MD5
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        // MessageDigest 工具 传入 MD5 算法 得到加密工具
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 接收类型是Byte - 通过Base64转码方便存储
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes())) ;
    }

    // 测试方法写的对不对 psvm
    public static void main(String[] args) {
        String md5 = null;
        try {
            md5 = getMD5Str("1234");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5);
    }

}
