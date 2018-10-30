package com.slowcampus.dao;

import com.slowcampus.dto.Image;
import lombok.extern.java.Log;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Log
public class ImageDaoImpl implements ImageDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public ImageDaoImpl(DataSource dataSource) {
        log.info("ImageDaoImpl()");
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("file");
    }


    @Override
    public List<Image> getList(Long boardId) {

        return null;
    }

    @Override
    public Image getImage(Long id) {
        return null;
    }

    @Override
    public Long uploadImage(Image image) {



        return 0L;
    }

    @Override
    public Long deleteImage(Long id) {
        return 0L;
    }
}
