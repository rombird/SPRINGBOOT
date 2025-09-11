package com.example.demo.Domain.Common.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    // int null 체크 X -> Integer 로 바꾸면 null 체크 가능
    private Integer pageNo;
    private Integer amount;
    private String keyword;
    private String type;
}
