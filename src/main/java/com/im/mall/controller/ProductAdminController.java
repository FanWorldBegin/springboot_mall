package com.im.mall.controller;

import com.github.pagehelper.PageInfo;
import com.im.mall.common.ApiRestResponse;
import com.im.mall.common.Constant;
import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.pojo.Product;
import com.im.mall.request.AddProductReq;
import com.im.mall.request.UpdateProductReq;
import com.im.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * 后台商品管理Controller
 */
@RestController  // 自动加RestBody
public class ProductAdminController {
    @Autowired
    ProductService productService;

    @PostMapping("admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.add(addProductReq);

        return ApiRestResponse.success();

    }

    @PostMapping("/admin/upload/file")
    public ApiRestResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename(); // 1. 获取原始名字
        // 2. 提取后缀名 - 最后一个点的位置
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        //3. 生成文件名 UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;

        // 4. 创建文件 - 生成一个文件夹
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        // 5. 生成文件
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);

        if(!fileDirectory.exists()) {
            // 6.当不存在文件夹时候尝试建立
            if(!fileDirectory.mkdir()) {
                //  fileDirectory.mkdir() 创建一个文件夹， 失败了抛出异常
                throw new MallException(MallExceptionEnum.MKDIR_FAILED);
            }
        }

        // 7. 将目标文件放入文件夹
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 8. 返回地址
        try {
            // 需要的是 StringBuffer  加上字符串
            // 返回一个完整的路径
            return ApiRestResponse.success(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/" + newFileName);
        } catch (URISyntaxException e) {
           return ApiRestResponse.error(MallExceptionEnum.FILE_UPLOAD_FAILED);
        }

    }

    private URI getHost(URI uri) {
        URI effectiveURI;

        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    @ApiOperation("后台更新商品")
    @PostMapping("/admin/product/update")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq) {
        Product product = new Product();
        // 获得 product 的 pojo
        BeanUtils.copyProperties(updateProductReq, product);

        productService.update(product);
        return ApiRestResponse.success();
    }


    @ApiOperation("后台删除商品")
    @PostMapping("/admin/product/delete")
    public ApiRestResponse deleteProduct(@RequestParam Integer id) {

        productService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台批量上下架商品")
    @PostMapping("/admin/product/batchUpdateSellStatus")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer[] ids, @RequestParam Integer sellStatus) {

        productService.batchUpdateSellStatus(ids, sellStatus);
        return ApiRestResponse.success();
    }


    @ApiOperation("后台商品列表接口")
    @PostMapping("/admin/product/list")
    public ApiRestResponse list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        PageInfo pageInfo = productService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

}
