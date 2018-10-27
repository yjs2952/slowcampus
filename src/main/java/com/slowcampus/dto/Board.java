package com.slowcampus.dto;

import lombok.Data;

import java.util.Date;

/*
 * @Data getter, setter, toString 자동 생성해줌
 */
@Data
public class Board {
    private Long id;
    private Long rootBoardId;
    private Long parentBoardId;
    private String title;
    private String userId;
    private String ipAddr;
    private int depth;
    private int depthOrder;
    private int readCount;
    private int category;
    private Date regDate;
    private Date modDate;
}
