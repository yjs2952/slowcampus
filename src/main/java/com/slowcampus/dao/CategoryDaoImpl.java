package com.slowcampus.dao;

import com.slowcampus.dto.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private NamedParameterJdbcTemplate jdbc;

    CategoryDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Category> getCategoryList() {
        String sql = "SELECT category as id, category_name as name FROM category ORDER BY category";

        try {
            RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
            return jdbc.query(sql, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
