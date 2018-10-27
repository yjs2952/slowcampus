package com.slowcampus.dto;

import lombok.Data;

import java.util.Date;

/*
 * @Data getter, setter, toString 자동 생성해줌
 */
@Data
public class Reply {
    private Long id;
    private Long boardId;
    private String content;
    private String userNickName;
    private String parentNickName;
    private String ipAddr;
    private int isDeleted;
    private Date regDate;
    private Date modDate;
}
