package com.slowcampus.dto;

import lombok.Data;

/*
 * @Data getter, setter, toString 자동 생성해줌
 */
@Data
public class Authority {
    private int authorityId;
    private String authorityName;
}
