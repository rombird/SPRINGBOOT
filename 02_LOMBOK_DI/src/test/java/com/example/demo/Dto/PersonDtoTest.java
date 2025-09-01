package com.example.demo.Dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDtoTest {
    @Test
    public void t1(){
        PersonDto dto = new PersonDto("홍길동", 50, "대구");
        System.out.println(dto);
    }

    // 이름, 나이, 주소 생성자 순서대로 입력해야되지만 builder 패턴을 생성하면 원하는 값만 넣을 수 있게됨
    @Test
    public void t2(){
        PersonDto dto = PersonDto.builder()
                .age(20)
                .name("티모")
                .build();
        System.out.println(dto);
    }

}