package com.slowcampus.dao;

import com.slowcampus.dto.Image;

import java.util.List;

public interface ImageDao {
    public List<Image> getList(Long boardId);

    public Image getImage(Long id);

    public int uploadImage(Image image);

    public int deleteImage(Long id);
}
