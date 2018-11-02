package com.slowcampus.dto;

import lombok.Data;

import java.util.Date;

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
}
