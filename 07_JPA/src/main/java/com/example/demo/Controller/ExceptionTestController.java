package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@Controller
@Slf4j
@RequestMapping("/except")
public class ExceptionTestController {
    // 모든 영역(Controller)에서 예외처리하는 방법

    // 특정한 영역(Controller)에서 예외처리하는 방법
    // filenotfoundexception에서 에러가 발생했을 때 여기서 처리하겠다는 의미
//    @ExceptionHandler(FileNotFoundException.class)
//    public String exception_handler1(Exception e, Model model){
//        log.error("error : " + e);
//        model.addAttribute("ex", e); // 모델에 전달 -> error.jsp 파일에 EX : ${ex} 하면 전달됨
//        return "except/error"; // 예외가 발생했을 때 에러 페이지를 호출
//    }
//
//    // ex2의 예외처리
//    @ExceptionHandler(ArithmeticException.class)
//    public String exception_handler2(Exception e){
//        log.error("error : " + e);
//        return "except/error";
//    }

    // 이 controller에서의 예외처리 -
//    @ExceptionHandler(Exception.class)
//    public String exception_handler1(Exception e, Model model){
//        log.error("Exception error : " + e);
//        model.addAttribute("ex", e); // 모델에 전달 -> error.jsp 파일에 EX : ${ex} 하면 전달됨
//        return "except/error"; // 예외가 발생했을 때 에러 페이지를 호출
//    }

    // 예외 억지로 만들어서 예외처리 반응 -
    @GetMapping("/ex1")
    public void ex1() throws FileNotFoundException{ // 예외객체 던져주기
        log.info("GET /except/ex1...");
        throw new FileNotFoundException("파일을 찾을 수 없습니다."); // 예외객체 생성
    }

    @GetMapping("/ex2/{num}/{div}")
    public String ex2(@PathVariable int num, @PathVariable int div, Model model) throws ArithmeticException {
        log.info("GET /except/ex2...");
        model.addAttribute("result", (num/div));
        return "except/ex2"; // page 위치 잡아주기(void 로 하면 오류 )
    }

    @GetMapping("/ex3")
    public void ex3(){
        log.info("GET /except/ex3...");
    }

}
