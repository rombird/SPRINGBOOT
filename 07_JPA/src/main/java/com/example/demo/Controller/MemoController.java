package com.example.demo.Controller;

import com.example.demo.Domain.Common.Dto.MemoDto;
import com.example.demo.Domain.Common.Dto.PageDto;
import com.example.demo.Domain.Common.Entity.Memo;
import com.example.demo.Domain.Common.Service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {


    @Autowired
    private MemoService memoService;

//    @ExceptionHandler(Exception.class)
//    public String exception_handler(Exception e){
//        log.error("MemoController's Exception..." + e);
//        return "memo/error";
//    }

    @GetMapping("/add")
    public void add_memo_get() throws Exception
    {
        log.info("GET /memo/add...");
    }
    @PostMapping("/add")
    public String add_memo_post(@Valid MemoDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception
    {
        log.info("POST /memo/add..." + dto);
        //파라미터
        //입력값검증(데이터)
        log.info("유효성 오류 발생여부 : " + bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            for(FieldError error  : bindingResult.getFieldErrors()){
              log.info("Error Field : "+error.getField()+" Error Message : "+error.getDefaultMessage());
                model.addAttribute(error.getField(),error.getDefaultMessage());
            }
            //throw new Exception("유효성 검증 오류!");
            return "memo/add";
        }

        //서비스 요청 -> Domain.Common.Service
//        boolean isAdded = memoService.memoRegistration(dto);
        Long insertedId = memoService.memoRegistration2(dto);
        if(insertedId!=null)
            redirectAttributes.addFlashAttribute("message","메모등록완료! : " + insertedId );
        //뷰로 이동
        return insertedId!=null ? "redirect:/":"memo/add";
    }

    // paging 작업하기 위해 필요한 코드
    @GetMapping("/list")
    public void list(
//            @RequestParam(value="pageNo", defaultValue="0") int pageNo, // -> 지정하지 않으면 기본값이 있어야하는데 defaultValue
//            @RequestParam(value="amount", defaultValue="10") int amount // 한페이지에 볼 페이지 건수를 제어
            PageDto pageDto,
            Model model
    ) throws Exception
    {
//        log.info("GET /memo/list... pageNo : " + pageNo + " amount : " + amount);
        log.info("GET /memo/list...pageDto" + pageDto);
        // PageAble 요청 객체를 여기서 만들어서 던지는 방법 or Service로 던지는 방법
        // 파라미터받기
        // 유효성체크 생략
        // 서비스 실행
        Page<Memo> page = memoService.listMemo(pageDto);
        // 뷰로 이동(+데이터) -> jsp 에서 적절하게 꺼내서 보여지도록
        model.addAttribute("page", page); // 프론트와 백엔드를 스프링부트로 할 거면(파일이 스프링부트내에 있다면 여기서) 만약 프론트와 백엔드를 나눠서 처리할 경우에는 이 방법을 사용 XX
        model.addAttribute("list", page.getContent()); // list를 꺼내도록

    }

}
