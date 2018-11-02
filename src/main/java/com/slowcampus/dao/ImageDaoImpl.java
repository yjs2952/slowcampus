package com.slowcampus.dao;

import com.slowcampus.dto.Image;
import lombok.extern.java.Log;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log
public class ImageDaoImpl implements ImageDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public ImageDaoImpl(DataSource dataSource) {
        log.info("ImageDaoImpl()");
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("file")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public List<Image> getImageList(Long boardId) {
        String sql = "SELECT id, original_name, save_name, path, size, type, board_id, regdate FROM file " +
                "WHERE board_id = :board_id";
        try {
            RowMapper<Image> rowMapper = BeanPropertyRowMapper.newInstance(Image.class);
            Map<String, Long> map = new HashMap<>();
            map.put("board_id",boardId);

            return jdbc.query(sql, map, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Image getImage(Long board_id) {
        String sql = "SELECT id, original_name, save_name, path, size, type, board_id, regdate FROM file " +
                "WHERE board_id = :board_id";
        try {
            RowMapper<Image> rowMapper = BeanPropertyRowMapper.newInstance(Image.class);
            Map<String, ?> map = Collections.singletonMap("board_id", board_id);
            return jdbc.queryForObject(sql, map, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long uploadImage(Image image) {
        String sqlImage = "INSERT INTO file(board_id, original_name, save_name, path, size, type, regdate) " +
                "VALUES(:boardId, :originalName, :saveName, :path, :size, :type, :regDate)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(image);
        jdbc.update(sqlImage,params);
        return image.getId();

//        return insertAction.executeAndReturnKey(params).longValue();
    }

    @Override
    public Long deleteImage(Long id) {
        return 0L;
    }
}
