package com.haotchen.syyimei.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haotchen.syyimei.entity.vo.QNObjectResult;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
public class QNObject {
    // 设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "sSjmp2xJ6ZJ_WZOEAX0lqHmF14Z36gNToFX7K2qE";
    String SECRET_KEY = "GkxUpiF-7Lt72o1OBlrM02rUbJQkPrhb14xZlrWo";

    // 要上传的空间（创建空间的名称）
    String bucketname = "haotchen-online";

    // 密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    Configuration cfg = new Configuration(Zone.zone1());
    // ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    // 使用的是测试域名
    private static String QINIU_IMAGE_DOMAIN = "http://qnimg.haotchen.top";

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public  QNObjectResult saveImage(MultipartFile file, String filename) throws IOException {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();

            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), filename + "." + fileExt, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                ObjectMapper objectMapper = new ObjectMapper();
                log.info(res.bodyString());
                QNObjectResult qnObjectResult = objectMapper.readValue(res.bodyString(), QNObjectResult.class);
                qnObjectResult.setDownloadUrl(QINIU_IMAGE_DOMAIN + "/" + filename + "." + fileExt);
                return qnObjectResult;
            } else {
                log.error("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            log.error("七牛异常:" + e.getMessage());
            return null;
        }
    }
}
