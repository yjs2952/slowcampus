package com.slowcampus.dao;

import com.slowcampus.dto.Authority;
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
import java.util.Collections;
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

    @Override
    public List<Authority> getMemberAuthority(Member member) {
        String sql = "SELECT authority.authority_id, authority.authority_name " +
                "FROM member_authority INNER JOIN authority ON member_authority.authority_id = authority.authority_id " +
                "WHERE user_id = :user_id";
        RowMapper<Authority> rowMapper = BeanPropertyRowMapper.newInstance(Authority.class);
        Map<String, ?> map = Collections.singletonMap("user_id", member.getId());
        try {
            return jdbc.query(sql, map, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int setMemberAuthority(Member member, Authority authority) {
        String sql = "INSERT INTO member_authority(user_id, authority_id) VALUES(:user_id, " +
                "(SELECT authority_id FROM authority WHERE authority_name = :authority_name))";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", member.getId());
        map.put("authority_name", authority.getAuthorityName());

        try {
            return jdbc.update(sql, map);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override

    public int deleteMemberAuthority(Member member, Authority authority) {
        /**
         * Multiple Checkboxes를 통한 권한 부여 및 제거의 경우,
         * 아래와 같이 각 계정에 대한 특정 authority_id를 제거해 주어야 하는 것이 맞다.
         * String sql = "DELETE FROM member_authority WHERE user_id = :user_id AND authority_id = " +
         *              "(SELECT authority_id FROM authority WHERE authority_name = :authority_name)";
         *
         * 현재는 Select list 이므로, 모든 authority_id를 제거하고, 새로 부여하는 방식을 선택하였다.
         */
        String sql = "DELETE FROM member_authority WHERE user_id = :user_id";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", member.getId());
//        map.put("authority_id", authority.getAuthorityId());

        try {
            return jdbc.update(sql, map);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
