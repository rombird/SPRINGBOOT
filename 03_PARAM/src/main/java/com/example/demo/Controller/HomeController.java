package com.example.demo.Controller;

// auto import
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home(){
//        System.out.println("GET /");
        log.info("GET / ...");
        return "index";
    }
    // localhost:8090/
    // WEB-INF/views/index.jsp가 들어오게됨 - 페이지 위치를 찾아줌
}
