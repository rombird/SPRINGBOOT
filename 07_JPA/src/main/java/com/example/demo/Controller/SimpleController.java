package com.example.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 사용자 요청을 받을 수 있는 bean을 사용할 수 있게 됨
@RequestMapping("/simple") // simple 내부에 있는 파일 중에 찾아서 사용, 모든 HTTP 메소드에 대해 사용할 수 있어 높은 유연성 제공
@Slf4j
public class SimpleController {

    // DemoApplication일 실행 후 웹에서 주소(http://localhost:8090/simple/test1) 입력하고 들어가면 콘솔창에 log.info에 적힌 내용 뜸
    // test1.jsp 파일을 넣어야함
    // method : 요청을 받아내는 상수값
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void test1(){
        log.info("GET /simple/test1...");
    }

    // http://localhost:8090/simple/test2 로 연결했을 때 -> WEB-INF/views/simple/abcd.jsp로 연결
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(){
        log.info("GET /simple/test2...");
        return "simple/abcd";
    }
    
    // 하나의 메소드에서 여러 HTTP 메소드를 처리하도록 설정가능 -> method로
    // URL에서 직접 경로 치고 들어가는 방식 : GET, POST 요청을 모두 받아들이면서 처리코드가 동일하다면 동시 적용
    @RequestMapping(value = "/test3", method = {RequestMethod.GET, RequestMethod.POST})
    public void test3(){ // return 값 없을 때 void로 꼭 바꿔주기!
        log.info("GET/POST /simple/test3...");
    }




}
