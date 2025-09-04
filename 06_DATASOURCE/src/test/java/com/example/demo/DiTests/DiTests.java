package com.example.demo.DiTests;

import com.example.demo.Component.PersonComponent;
import com.example.demo.Dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class DiTests {
    // 의존주입
    
    @Autowired
    private PersonComponent personComponent;

    @Test
    public void t1(){
        System.out.println(personComponent);
    }
    // bean으로 넣는 작업 PersonComponent에서
    @Autowired
    private PersonDto personDto;
    // PersonDto에서 만든 personDto, person03 둘 다 있기 때문에 지정해주지 않으면 이름으로 구별!
    // bean 생성시 이름 지정 가능

    @Test
    public void t2(){
        System.out.println(personDto);
    }
    // PersonDtoConfig에서 만든 객체 테스트
    // 01
    @Autowired
    private PersonDto person03; // 이름 중요! 잘 맞춰서 지정해줘야함
    @Test
    public void t3(){
        System.out.println(person03);
    }

    // 02
    // person4의 bean 이름 지정시 지정한 bean 이름으로 접근
    @Autowired
    private PersonDto personBean;
    @Test
    public void t4(){
        System.out.println(personBean);
    }


    // ApplicationContext 에서 꺼내보는 작업
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void t5(){
        System.out.println(applicationContext.getBean("personBean"));
        System.out.println(applicationContext.getBean("person03"));
    }

}
