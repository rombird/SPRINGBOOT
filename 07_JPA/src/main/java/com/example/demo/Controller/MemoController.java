package com.example.demo.Controller;

import com.example.demo.Domain.Common.Dto.MemoDto;
import com.example.demo.Domain.Common.Service.MemoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

    // controller와 service 연결작업
    @Autowired
    private MemoService memoService;

    // MemoController에서 발생하는 모든 exception 여기서 받겠다
//    @ExceptionHandler(Exception.class)
//    public String exception_handler(Exception e){
//        log.error("MemoController's Excetpion ...");
//        return "memo/error";
//    }

    @GetMapping("/add")
    public void add_memo_get() throws Exception {
        log.info("GET /memo/add...");
    }

    // @Valid @ModelAttribute MemoDto dto -> 유효성체크를 하기위해 추가
    @PostMapping("/add")
    public String add_memo_post(@Valid @ModelAttribute MemoDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception  {
        log.info("POST /memo/add..." + dto);
        // 파라미터
        // 입력값 검증(데이터)
        log.info("유효성 오류 발생 여부 : "+bindingResult.hasErrors());
        if(bindingResult.hasErrors()){ // 오류가 발견되었다면
            for(FieldError error : bindingResult.getFieldErrors()) {
                log.info("Error Field" + error.getField() + "Error Message : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage()); // key-value 형태로 모델에 전달
            }
            // throw new Exception("유효성 검증 오류"); // throw new NullPointerException("예외발생!");
            return "memo/add"; // 예외가 발생하면 memo add로 가도록
        }

        // 결측치 확인, null인지 아닌지 확인
        // id 양수값, 1000번까지만 받을 수 있도록
        // 서비스 요청 -> Domain, Common, Service(역할별로 domain 나눔)
        // RedirectAttributes redirectAttributes 연결하고
//        boolean isAdded = memoService.memoRegistration(dto); // 검증이 끝난 dto로 전달-> true, false 로 반환
//        if(isAdded) // isAdded가 true 면 메시지 하나 주기
//            redirectAttributes.addFlashAttribute("message","메모 등록 완료");
        // 메시지를 속성에 등록하면 message라는 속성명을 가지게 되는데 index.jsp 파일에서 작업 ->  ${message}
        // 뷰로 이동
        // memo추가를 했으면 페이지 이동을 해야함 -> void -> String
//        return (isAdded)?"redirect:/":"memo/add"; // isAdded가 true면 redirect로 이동하고 아니면 계속 memo/add에 머무르도록


        Long insertedId = memoService.memoRegistration2(dto);
        if(insertedId!=null)
            redirectAttributes.addFlashAttribute("message", " 메모등록완료! : " + insertedId);
        // 뷰로 이동
        return insertedId!=null?"redirect:/":"memo/add";

    }

}
