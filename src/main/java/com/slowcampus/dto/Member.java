package com.slowcampus.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/*
 * @Data getter, setter, toString 자동 생성해줌
 */
@Data
public class Member {
    private String id;
    private String password;
    private String nickname;
    private String email;
    private Date regdate;
    private Date moddate;
    private List<Authority> authorityList;
}
