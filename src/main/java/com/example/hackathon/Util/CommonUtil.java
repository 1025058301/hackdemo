package com.example.hackathon.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Random;

public class CommonUtil {
    public static Logger logger= LoggerFactory.getLogger(CommonUtil.class);

    public static final String[] imageExt=new String[]{"png","jpg","jpeg","bmp"};

    public static final String[] videoExt=new String[]{"mp4","avi","rmvb","flv"};

    public static final String fileSavePath="/Users/bytedanc/Desktop/image/";

    public static final String videoSavePath="/Users/bytedanc/IdeaProjects/test/src/video";

    public static String Domain="http://127.0.0.1:8080/";

    public static String QiniuDomain= "http://qxevtrp1k.hn-bkt.clouddn.com/";

    public static String getJsonString(int code){
        JSONObject jsonObejct=new JSONObject();
        jsonObejct.put("code",code);
        return  jsonObejct.toJSONString();
    }

    public static String getJsonString(int code,String msg){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return  jsonObject.toJSONString();
    }

    public static String getJsonString(int code, Map<String,Object> map){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",code);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            jsonObject.put(entry.getKey(),entry.getValue());
        }
        return  jsonObject.toJSONString();
    }

    public static boolean isFileAllowed(String fileExt){
        for (String ext:imageExt
        ) {
            if(fileExt.equals(ext)){
                return true;
            }
        }
        return false;
    }


}

