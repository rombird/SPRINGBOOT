package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// alt + 7 -> structure
// ctrl + shift + t : 테스트 자동 생성
//@Getter
//@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data // lombok에서 사용가능한 애노테이션 생성자, 따로 만들지 않아도 OK 단 생성자는 직접 해줘야함(getter, setter, tostring)
@Builder // 객체 생성하는 시점에서 생성자 순서 상관 X, 모든 생성자를 다 입력하지 않아도 OK

public class PersonDto {
    private String name;
    private int age;
    private String addr;
}
