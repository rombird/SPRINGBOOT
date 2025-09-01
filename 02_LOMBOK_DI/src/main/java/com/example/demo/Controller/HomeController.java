package com.example.demo.Controller;

// auto import : setting ->
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
    // WEB-INF/views/index.jsp가 들어오게됨
}
