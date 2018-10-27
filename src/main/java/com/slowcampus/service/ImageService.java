package com.slowcampus.service;

import com.slowcampus.dto.Image;

import java.util.List;

public interface ImageService {
    public List<Image> getList(Long boardId);

    public Image getImage(Long id);

    public int uploadImage(Image image);

    public int deleteImage(Long id);
}
