package com.slowcampus.dto;

import lombok.Data;

import java.util.Date;

/*
 * @Data getter, setter, toString 자동 생성해줌
 */
@Data
public class Image {
    private Long id;
    private String orginalName;
    private String savedName;
    private String path;
    private String type;
    private Long size;
    private Long boardId;
    private Date regDate;
}
