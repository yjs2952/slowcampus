package com.slowcampus.service;

import com.slowcampus.dao.ImageDao;
import com.slowcampus.dto.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public List<Image> getList(Long boardId) {
        return null;
    }

    @Override
    public Image getImage(Long id) {
        return imageDao.getImage(id);
    }

    @Override
    public int uploadImage(Image image) {
        return 0;
    }

    @Override
    public int deleteImage(Long id) {
        return 0;
    }
}
