package com.example.hackathon.Service;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
public class AudioService {

    public Logger logger = LoggerFactory.getLogger(VideoService.class);

    public Object callPythonAudioProcess(String videoPath) {
        // 访问服务进程的套接字
        Socket socket = null;
        try {
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket("10.1.58.147", 12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);

            // 发送内容
            // 1. 发送mp4文件的路径，则表示开始文字识别和敏感词识别，返回结果：text｜敏感词字符串
            //out.print("/Users/bytedance/Desktop/pythonprojects/VideoText/data/test3.mp4");

            // 2。 发送普通字符串，表示添加敏感词到词库，返回sucess、failed、exists三种字符串
            out.print("/Users/bytedanc/IdeaProjects/test/src/video/test3.mp4");

            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while ((tmp = br.readLine()) != null)
                sb.append(tmp).append('\n');
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

}

