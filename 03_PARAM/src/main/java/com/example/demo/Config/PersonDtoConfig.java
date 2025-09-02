package com.example.demo.Config;


import com.example.demo.Dto.PersonDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonDtoConfig {

    // person03이라는 이름의 personDto가 만들어지게 됨
    @Bean
    public PersonDto person03(){
        return PersonDto.builder()
                .name("강동원")
                .addr("서울")
                .age(40)
                .build();
    }

    @Bean(name = "personBean")
    public PersonDto person04(){ // 함수명이 기본 bean 의 이름이 됨
        return PersonDto.builder()
                .name("버즈")
                .addr("서울")
                .age(50)
                .build();
    }


}
