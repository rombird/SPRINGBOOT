package com.example.demo.Component;

import lombok.Data;
import org.springframework.stereotype.Component;

// applicationcontext bean이 하나 만들어진 것으로 봄
// -> personComponent bean이름을 명시하지 않으면 지정하지 않은 클래스명의 소문자를 따서 지정됨
@Component
@Data // lombok annotation - 문자열 받기위한 getter, setter, tostring 작업
public class PersonComponent {
    private String name;
    private int age;
    private String addr;

    PersonComponent(){
        this.name="홍길동";
        this.age=24;
        this.addr="대구";
    }
}
