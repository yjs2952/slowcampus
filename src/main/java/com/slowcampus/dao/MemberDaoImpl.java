package com.slowcampus.dao;

import com.slowcampus.dto.Board;
import com.slowcampus.dto.Member;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("memberDao")
@Log
public class MemberDaoImpl implements MemberDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    @Autowired
    public MemberDaoImpl(DataSource dataSource) {
        log.info("MemberDao()");
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member");
    }

    @Override
    public Member loginMember(Member member) {
        String sql = "SELECT id, nickname, email FROM member WHERE id = :id AND password = :password";

        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", member.getId());
            map.put("password", member.getPassword());
            RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);

            List<Member> loginMember = jdbc.query(sql, map, rowMapper);
            if (loginMember.size() == 1) {
                return loginMember.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void logoutMember() {

    }

    @Override
    public int signupMember(Member member) {
        String sql = "INSERT INTO member(id, password, nickname, email) " +
                "VALUES(:id, :password, :nickname, :email)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);

        try {
            return jdbc.update(sql, params);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
