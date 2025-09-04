package com.example.demo.Controller;


import com.example.demo.Domain.Common.Dto.MemoDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

    // MemoController에서 발생하는 모든 exception 여기서 받겠다
//    @ExceptionHandler(Exception.class)
//    public String exception_handler(Exception e){
//        log.error("MemoController's Excetpion ...");
//        return "memo/error";
//    }


    // 임의로 데이트타입 형변환하는 작업
    // 바인드 처리를 직접 하기위한 작업(data_test 직접 문자열로 받기위한 작업) - 먼저 동작
    @InitBinder
    public void dataBinder(WebDataBinder webDataBinder) {
        log.info("MemoController's dataBinder ..." + webDataBinder);
        // (localdate.class(replection), 내가 관여할 필드, 어떻게 관여할 건지 지정)
        webDataBinder.registerCustomEditor(LocalDate.class, "data_test", new DataTestEditor());
    }

    private static class DataTestEditor extends PropertyEditorSupport {
        // data binding을 해주는 과정에서 얘네가 작업을 해주겠다는 의미
        // 생성 -> 메소드 재정의 -> setAsText 선택
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            log.info("DataTestEditor's setAsText text : " + text); // data_test에 입력한 값이 들어오는 걸 확인
            LocalDate date = null;
            if(text.isEmpty()){
                // 아무것도 입력하지않으면 현재 날짜로
                date = LocalDate.now();
            }else{
                // 입력했다면 format 확인(yyyy#MM#dd)
                text = text.replaceAll("#", "-"); 
                date = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // parsing을 이렇게 진행하겠다
            }
            setValue(date); // 변환한 데이터 담아주기
        }
    }

    @GetMapping("/add")
    public void add_memo_get() throws Exception {
        log.info("GET /memo/add...");
    }

    @PostMapping("/add")
    public void add_memo_post(@Valid @ModelAttribute MemoDto dto, BindingResult bindingResult, Model model) throws Exception  {
        log.info("POST /memo/add..." + dto);
        // 파라미터
        // 입력값 검증
        log.info("유효성 오류 발생 여부 : "+bindingResult.hasErrors());
        if(bindingResult.hasErrors()){ // 오류가 발견되었다면
            for(FieldError error : bindingResult.getFieldErrors()){
                log.info("Error Field" + error.getField() + "Error Message : "+error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            // throw new NullPointerException("예외발생!");
            throw new Exception("유효성 검증 오류");
        }

        // 결측치 확인, null인지 아닌지 확인
        // id 양수값, 1000번까지만 받을 수 있도록
        // 서비스 요청 -> Domain, Common, Service(역할별로 domain 나눔)
        // 뷰로 이동
    }

}
