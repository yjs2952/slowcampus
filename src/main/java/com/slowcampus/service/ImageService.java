package com.slowcampus.service;

import com.slowcampus.dto.Image;

import java.util.List;

public interface ImageService {

    /*
     UploadController 에서 MultipartFile 파라미터를 이용해 file을 서비스로 보낸다?
     컨트롤러에서
     1. 파일 이름 새로 생성.
     2. 파일 복사.
     3-1. 파일을 서버에 업데이트.
     3-2. 파일을 DB에 저장.

     DB에 저장해야할거????
     URL 만 저장해주면 되지 않나?





     */


    public List<Image> getList(Long boardId);

    public Image getImage(Long board_id);

    public int uploadImage(Image image);

    public int deleteImage(Long id);
}
