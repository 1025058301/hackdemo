package com.example.hackathon.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.hackathon.Util.CommonUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
@Service
public class QiniuyunService {
    public static Logger logger= LoggerFactory.getLogger(QiniuyunService.class);

    Configuration cfg=new Configuration(Region.region2());
    UploadManager uploadManager=new UploadManager(cfg);
    String AccessKey="ZiJXZMhWzY_1YyHzaQSg7ONH5pgdku-lage9V4hl";
    String SecretKey="gvwzt4GhVtJYckhtyZ5pWS_i6KFWCJ_wF4VnOOke";
    //存储空间名
    String bucketName="hackton-fourthteam";
    Auth auth=Auth.create(AccessKey,SecretKey);

    private String getupLoadToken(){
        return auth.uploadToken(bucketName);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try{
            int dotIndex = file.getOriginalFilename().lastIndexOf(".");
            if (dotIndex < 0) {
                return null;
            }
            String fileExt=file.getOriginalFilename().substring(dotIndex+1).toLowerCase();
            String fileName= UUID.randomUUID().toString().replaceAll("-","")+"."+fileExt;
            Response response=uploadManager.put(file.getBytes(),fileName,getupLoadToken());
            if(!(response.isOK()&&response.isJson())){
                logger.error("七牛异常"+response.bodyString());
                return null;
            }
            return CommonUtil.QiniuDomain+ JSONObject.parseObject(response.bodyString()).get("key");
        }catch (QiniuException e){
            logger.error(e.getMessage());
            return null;
        }
    }
    public String saveImage(InputStream inputStream,String userId,int index) throws IOException {
        try{
            String fileName= "image_"+userId+"_"+index+".jpg";
            Response response=uploadManager.put(inputStream,fileName,getupLoadToken(),null,null);
            if(!(response.isOK()&&response.isJson())){
                logger.error("七牛异常"+response.bodyString());
                return null;
            }
            return CommonUtil.QiniuDomain+ JSONObject.parseObject(response.bodyString()).get("key");
        }catch (QiniuException e){
            logger.error(e.getMessage());
            return null;
        }
    }

/*    public String saveVideo(InputStream inputStream,String userId)throws IOException{
        try{
            String fileName= "image_"+userId+"_"+index+".jpg";
            Response response=uploadManager.put(inputStream,fileName,getupLoadToken(),null,null);
            if(!(response.isOK()&&response.isJson())){
                logger.error("七牛异常"+response.bodyString());
                return null;
            }
            return CommonUtil.QiniuDomain+ JSONObject.parseObject(response.bodyString()).get("key");
        }catch (QiniuException e){
            logger.error(e.getMessage());
            return null;
        }
    }*/

}
