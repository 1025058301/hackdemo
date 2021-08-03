package com.example.hackathon.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VideoController {
    public Logger logger= LoggerFactory.getLogger(VideoController.class);

    @RequestMapping("/upload")
    public String upload(Model model,
                        @RequestParam("video")MultipartFile file) {
        try {
            int dotIndex = file.getOriginalFilename().lastIndexOf(".");
            String fileExt=file.getOriginalFilename().substring(dotIndex+1).toLowerCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
