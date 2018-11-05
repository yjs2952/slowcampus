package com.slowcampus.controller;

import com.slowcampus.util.AzureApp;
import com.slowcampus.dao.ImageDao;
import com.slowcampus.dto.Image;
import com.slowcampus.service.ImageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Controller
@Log
public class UploadController {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/uploadForm" , method= RequestMethod.GET)
    public void uploadForm() {

    }

//    @RequestMapping(value="/uploadForm" , method = RequestMethod.POST)
//    public void uploadForm(MultipartFile file, Model model) {
//        // lombok 플로그인 이용.
////        System.out.println(files.length);
////        System.out.println(files.getClass());
//
//
//        log.info("originalName : " + file.getOriginalFilename());
//        log.info("size : " + file.getSize());
//        log.info("contentType : " + file.getContentType());
//        log.info("getName : " + file.getName());
//
//
//
//        /*
//         과정을 좀 더 세세하게 바꿔야 한다.
//         1. MultipartFile 정보를 가지고 임시 폴더에 파일을 새로 만든다.
//         그 과정에서 필요한 정보및 이후에
//         필요한 정보들 saveName,originalName, path, size, contentType 이런것들을 DB에 넣어준다.
//
//         2.그리고 임시파일을 이용해. AzureApp.class 에 있는 sourceFile을
//         sourceFile = new File(이름, 경로); 해서 만들고 Azure 에 올려준다??
//
//         3. 임시 파일들을 삭제한다.
//
//         해야하는 것
//         -기능을 좀 세세하게 나눠야 한다.
//         -MultipartFile[] 로 하면! 1개일 경우와 여러개 일때!
//          메소드를 나눠야 할까?? 반복문에 넣으면. 어차피 한번만 반복되니까... 흠.
//          아 Dao 에서 getImageList, getImage 나눠야 하는구나.
//         -임시 파일 삭제는 되는데 폴더 삭제가 안된다.  (complete)
//         (form에 multiple 설정해주면 List로 받아올 수 있다???)
//
//
//
//         */
//        //List<MultipartFile> fileList =  ??
//        //MultipartFile[]    ??
//
//        File dir = new File("/tmp");
//        System.out.println("최초 File('.') 했을때! : " + dir.getAbsolutePath());
//        String path = dir.getAbsolutePath();
//
//        String datePath = AzureApp.calcPath(path);
//
//        path = path + datePath;
//
//        UUID uid = UUID.randomUUID();
//        String savedName = uid.toString() + "_" + file.getOriginalFilename();
//
//        File sourceFile = new File(path , savedName);
//
//        AzureApp app = new AzureApp();
//        try {
//            FileCopyUtils.copy(file.getBytes(),sourceFile);
//            String url = datePath.substring(1) + File.separator +sourceFile.getName();
//            System.out.println("UploadController의 url : " + url);
//            System.out.println("UploadController의 path : " + path);
//
//
//
//            app.execAzure(url,sourceFile.getAbsolutePath());
//        } catch(IOException e) {
//            e.printStackTrace();
//        } finally {
//            sourceFile.delete();
//            // 폴더 삭제용.
//            path = path + File.separator;
//            System.out.println("path+File.separator : " + path);
//            File deleteDir = new File(path);
//            deleteDir.delete();
//
//        }
//
//    }



    @RequestMapping(value="/uploadForm" , method = RequestMethod.POST)
    public void uploadForm(MultipartFile[] files, Model model) {
        // lombok 플로그인 이용.


        /*
         과정을 좀 더 세세하게 바꿔야 한다.
         1. MultipartFile 정보를 가지고 임시 폴더에 파일을 새로 만든다.
         그 과정에서 필요한 정보및 이후에
         필요한 정보들 saveName,originalName, path, size, contentType 이런것들을 DB에 넣어준다.

         2.그리고 임시파일을 이용해. AzureApp.class 에 있는 sourceFile을
         sourceFile = new File(이름, 경로); 해서 만들고 Azure 에 올려준다??

         3. 임시 파일들을 삭제한다.

         해야하는 것
         -기능을 좀 세세하게 나눠야 한다.
         -MultipartFile[] 로 하면! 1개일 경우와 여러개 일때!
          메소드를 나눠야 할까?? 반복문에 넣으면. 어차피 한번만 반복되니까... 흠.
          아 Dao 에서 getImageList, getImage 나눠야 하는구나.
         -임시 파일 삭제는 되는데 폴더 삭제가 안된다.  (complete)
         (form에 multiple 설정해주면 List로 받아올 수 있다???)



         */
        //List<MultipartFile> fileList =  ??
        //MultipartFile[]    ??



        for(MultipartFile file : files) {

            /* 기본 폴더 설정, 날짜 경로 더해주기는 파일들의 공통적인 부분. for 위로?*/
            File dir = new File("/tmp");
            String path = dir.getAbsolutePath();

            String datePath = AzureApp.calcPath(path);

            // /tmp/2018_11_01
            path = path + datePath;

            // savedName : ~~~~~~~~~~~~~_원래이름.
            UUID uid = UUID.randomUUID();
            String savedName = uid.toString() + "_" + file.getOriginalFilename();

            File sourceFile = new File(path , savedName);


            Image image = new Image();

            image.setOriginalName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setSize(file.getSize());
            image.setSaveName(savedName);
            image.setPath(datePath + File.separator +savedName);
            image.setRegDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            image.setBoardId(3L);// 임시번호. 원래는 파라미터로 받아야함.

            imageService.uploadImage(image);
            // 이미지 db업로드 완료.

            imageService.uploadImageToAzure(file,sourceFile,datePath,path);

        }


    }


}
