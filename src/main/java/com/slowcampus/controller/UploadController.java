package com.slowcampus.controller;

import com.slowcampus.Storage.AzureApp;
import com.slowcampus.dto.Image;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log
public class UploadController {

    @RequestMapping(value="/uploadForm" , method= RequestMethod.GET)
    public void uploadForm() {

    }

    @RequestMapping(value="/uploadForm" , method = RequestMethod.POST)
    public void uploadForm(MultipartFile file, Model model) {
        // lombok 플로그인 이용.
        log.info("originalName : " + file.getOriginalFilename());
        log.info("size : " + file.getSize());
        log.info("contentType : " + file.getContentType());



        AzureApp app = new AzureApp();
        try {
            app.execAzure(file.getOriginalFilename(), file.getBytes());
        } catch(IOException e) {
            e.printStackTrace();
        }




    }
}
