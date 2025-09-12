package com.example.demo.Controller;

import com.example.demo.Domain.Common.Dto.MemoDto;
import com.example.demo.Domain.Common.Service.MemoServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {


    @Autowired
    private MemoServiceImpl memoService;

//    @ExceptionHandler(Exception.class)
//    public String exception_handler(Exception e){
//        log.error("MemoController's Exception..." + e);
//        return "memo/error";
//    }

    // 요청에 맞는 페이지 이름
    @GetMapping("/add")
    public void add_memo_get() throws Exception
    {
        log.info("GET /memo/add...");
    }

    @PostMapping("/add")
    public String add_memo_post(@Valid MemoDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception
    { // 유효성 검증을 위해 valid 추가
        log.info("POST /memo/add..." + dto);
        //파라미터
        //입력값검증(데이터)
        log.info("유효성 오류 발생여부 : " + bindingResult.hasErrors());
        if(bindingResult.hasErrors()){ // 필트 에러가 있다면 에러를 하나씩 읽으면서
            for(FieldError error  : bindingResult.getFieldErrors()){
              log.info("Error Field : "+error.getField()+" Error Message : "+error.getDefaultMessage());
                model.addAttribute(error.getField(),error.getDefaultMessage()); // 모델에 저장
            }
            //throw new Exception("유효성 검증 오류!");
            return "memo/add"; // 반복작업이 끝나면 memo.add
        }

        //서비스 요청 -> Domain.Common.Service
//        boolean isAdded = memoService.memoRegistration(dto);
        Long insertedId = memoService.memoRegistration2(dto); // 추가된 Id값
        if(insertedId!=null)
            redirectAttributes.addFlashAttribute("message","메모등록완료! : " + insertedId );
        //뷰로 이동
        return insertedId!=null ? "redirect:/":"memo/add";
    }

}
