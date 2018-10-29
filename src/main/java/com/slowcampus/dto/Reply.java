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
    private String userNickname;
    private String parentNickname;
    private Long groupId;
    private int depth;
    private String ipAddr;
    private int isDeleted;
    private Date regdate;
    private Date moddate;
}
