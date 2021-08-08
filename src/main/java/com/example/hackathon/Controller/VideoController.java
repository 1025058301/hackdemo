package com.example.hackathon.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hackathon.Service.AudioService;
import com.example.hackathon.Service.QiniuyunService;
import com.example.hackathon.Service.VideoService;
import com.example.hackathon.Util.CommonUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.*;

@Controller
public class VideoController {
    public Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    QiniuyunService qiniuyunService;

    @Autowired
    VideoService videoService;

    @Autowired
    AudioService audioService;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(
            @RequestParam("video") MultipartFile file,@RequestParam("userId") String userId) throws IOException {
        Frame frame = null;
        try {
            Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
            InputStream videoSaveStream=file.getInputStream();
            InputStream getFrameStream=file.getInputStream();
            String videoLocalPath=videoService.saveVideoInLocal(videoSaveStream,file.getOriginalFilename());
            audioService.callPythonAudioProcess(videoLocalPath);
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(getFrameStream);
            fFmpegFrameGrabber.start();
            int frameSum = fFmpegFrameGrabber.getLengthInFrames();
            System.out.println("视频的总帧数是" + frameSum);
            System.out.println("视频的帧率是" + fFmpegFrameGrabber.getFrameRate());
            System.out.println("视频的时长是是" + fFmpegFrameGrabber.getLengthInTime());
            System.out.println("开始对视频进行取帧");
            int nextIndex=0;
            int space=20;//取帧的间隔
            for (int i = 0; i < frameSum; i++) {
                frame = fFmpegFrameGrabber.grabFrame();
                System.out.println(frame.image==null?"image为空":"image不为空");
                if (frame.image!=null&&i>=nextIndex) {
                    BufferedImage bi = java2DFrameConverter.getBufferedImage(frame);
                    String saveFileName = CommonUtil.fileSavePath + "image" + "_" + i + ".jpg";
                    System.out.println("第"+i+"张图片的保存地址为"+saveFileName);
                    File saveFile = new File(saveFileName);
                    ImageIO.write(bi, "jpg", saveFile);
                    FileInputStream fileInputStream=new FileInputStream(saveFile);
                    qiniuyunService.saveImage(fileInputStream,userId,i);
                    nextIndex=i+space;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("出现异常" + e.getMessage());
        }
        return CommonUtil.getJsonString(200);
    }

    @RequestMapping("/callpython")
    @ResponseBody
    public String callpython() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "123");
        String str = jsonObject.toJSONString();
        // 访问服务进程的套接字
        Socket socket = null;
        try {
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket("127.0.0.1",12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            // 解析结果
            System.out.println(sb);

            return CommonUtil.getJsonString(200);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
            logger.info("远程接口调用结束.");
        }
        return CommonUtil.getJsonString(100);
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
