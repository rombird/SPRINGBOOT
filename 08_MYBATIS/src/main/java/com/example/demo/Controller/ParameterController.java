package com.example.demo.Controller;

import com.example.demo.Dto.PersonDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/param")
public class ParameterController {
    @RequestMapping(value = "/p01", method= RequestMethod.GET)
    public void paramHandler_1(@RequestParam(name="username", required = false) String name){ // 처리기(Handler)가 url에 적절하게 매핑됨을 알수 있음
        log.info("GET /param/p01..." + name);
    } // 웹에서 http://localhost:8090/param/p01 치면 log.info 내용이 콘솔창에 출력됨
    // 2. @RequestParam(name="username") String name -> 추가시
    // http://localhost:8090/param/p01?name=홍길동    (? : 쿼리 스트링)
    // username="홍길동"으로 받겠다고 한다면 name 설정 꼭 해주기
    // required=false를 추가하면 파라미터가 없어도 name이 null로 출력


    // required=true : 반드시 parameter를 받아야되는 형태
    @GetMapping("/p02")
    public void paramHandler_2(@RequestParam(name="username", required = true) String name){
        log.info("GET /param/p02..." + name);
    }

    // post -> body에서 지정(postman 사용시)
    @PostMapping("/p03")
    public void paramHandler_3(@RequestParam(name="username", required = false) String name){
        log.info("POST /param/p03..." + name);
    }

    // @RequestParam : 동기요청 파라미터 처리 / html form 기반 전달되는 파라미터 처리(JS form-data도 받기 가능, JSON Type 받기 불가)
    // @RequestBody : 비동기요청 파라미터 처리 / json, filedata 등 전달되는 파라미터 처리(html form 처리가능, form-data에 추가시 옵션 필요)
    @PostMapping("/p04")
    public void paramHandler_4(@RequestBody String name){
        log.info("POST /param/p04..." + name);
    }
    // json, filedata를 주고 받을 때는 requestBody, 그 외는 requestParam 이라고 생각

    @PostMapping("/p05")
    public void paramHandler_5(@RequestParam(name="username", required = false, defaultValue = "홍길동") String name){
        // 파라미터를 지정하지 않더라도 기본 파라미터 값을 전달 할 수 있음 -> name에 홍길동 들어간 것 확인
        log.info("POST /param/p05..." + name);
    }

    // 파라미터 여러개 받는 작업
    @GetMapping("/p06")
    public void paramHandler_6(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String addr
    ){
        log.info("Get /param/p06..."+name+ " " +age+" "+addr);
    }

    // PersonDto으로 한번에 이름, 나이, 주소 받기 -> @ModelAttribute PersonDto dto / PesronDto dto(@ModelAttribute 생략도 가능)
    @GetMapping("/p07")
    public void paramHandler_7(PersonDto dto){
        log.info("Get /param/p07..."+dto);
    }

    // 파라미터를 받는 애노테이션 : @RequestParam,  @RequestBody, @ModelAttribue, @PathVariable 정리 잘하기
    // http://localhost:8090/param/p08/홍길동/55/대구 : 파라미터인지 아닌지 경로를 헷갈리게 하려는 용도
    @GetMapping("/p08/{name}/{age}/{addr}")
    public void paramHandler_8(
            @PathVariable(value = "name") String username,
            @PathVariable int age,
            @PathVariable String addr
    ){
        log.info("Get /param/p08..."+username+ " " +age+" "+addr);
    }

    // 파라미터 매핑의 속성 순서와 일치해야함
    @GetMapping("/p09/{name}/{age}/{addr}")
    public void paramHandler_9(PersonDto dto){
        log.info("Get /param/p09..."+dto);
    }
    // http://localhost:8090/param/p09/홍길동/55/대구

    // 뷰페이지 연결
    // localhost:8090/param/page1로 요청하면 /WEB-INF/views/+param/page1+.jsp
    @GetMapping("/page1")
    public void page1(PersonDto dto, Model model){ //뷰로 데이터를 전달하기 위해 Model model 많이 사용(model - 의존 주입을 이미 받고있다는 의미)
        log.info("GET /param/page1... "+dto);
        // 01 파라미터 받기
        // http://localhost:8090/param/page1?name=홍길동&age=55&addr=대구 -> log.info 출력됨 -> 던진 내용 뷰로 전달
        // 02 유효성 검증
        // 03 서비스 실행

        model.addAttribute("dto",dto);
        model.addAttribute("isLogin", true);
        // 04 뷰로 이동(자동)

    }
    //
    @GetMapping("/page2/{name}/{age}/{addr}") // URL 형태 지정
    public ModelAndView page2_handler(PersonDto dto){ // dto의 속성명과 일치하면 알아서 파라미터가 받아짐
        log.info("GET /param/page2..."+dto);

        // 지정한 뷰 경로와 속성값을 전달 할 준비를 해야함
        // 지정 X -> localhost:8090/param/page2/홍길동/55/대구 로 URL을 지정시 page2가 만들어졌지만 뜨지 X
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto",dto); // name, value 입력
        modelAndView.setViewName("param/page2");
        return modelAndView;
    }

    // 서블릿 방법으로 파라미터 받아보기
    // http://localhost:8090/param/page1?name=홍길동&age=55&addr=대구  로 실행
    @GetMapping("/page3")
    public void page3_handler(HttpServletRequest request, HttpServletResponse response){

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age")); // 문자형을 숫자로 형변환(쿼리 스트링은 모두 문자열)
        String addr = request.getParameter("addr");
        log.info("GET /param/page3..."+name+" "+age+" "+addr);

        PersonDto dto = new PersonDto(name, age, addr);
        request.setAttribute("dto", dto); // forward 방식
        // 기본적으로 request는 디폴트가 forward -> 내용 넣으니까 확인 바로 가능
        //  세션으로 넣을 때 jsp page 설정에서 session="false" 때문에 내용입력이되지 않음

        // 모델 안쓰고 세션에다 속성값을 dto로 넣어보는 작업
//        HttpSession session = request.getSession();
//        session.setAttribute("dto", dto);

        Cookie cookie = new Cookie("c1", "v1"); // class jakarta
        response.addCookie(cookie);

    }

    // View -> Controller -> Service -> DataAceessObject
    //      파라미터, 유효성, 서비스 -> 비즈니스 로직처리 -> DBCRUD
    // Map<String, Object>로 param을 받음 URL에서 아무거나 지정시 아무거나 다 들어감
    // localhost:8090/param/page4?name=강동원&height=181&weight=70
    // {name=강동원, height=181, weight=70} 출력
    @GetMapping("/page4")
    public void page4_handler(@RequestParam Map<String, Object> params){
        log.info("GET /param/page4..."+params);
    }

    // Forward 처리 - 빈도수는 떨어지지만 알고는 있어야함 ( forward: )
    // 내가원하는 또다른 페이지로 이동가능함, request가 유지가 된채 이동
    // 클라이언트가 한 번 요청했는데 forward로 여러번 이동 가능
    // ---------------------
    // http://localhost:8090/param/forward/
    //
    @GetMapping("/forward/init")
    public String forward_init_handler(Model model){
        log.info("GET /param/forward/init...");
        model.addAttribute("init", "init_value");
        return "forward:/param/forward/step1"; // reqeust, response 사용 없이 forward 가능
    }
    @GetMapping("/forward/step1")
    public String forward_step1_handler(Model model){
        model.addAttribute("step1", "step1_value");
        log.info("GET /param/forward/step1");
        return "forward:/param/forward/step2";
    }
    @GetMapping("/forward/step2")
    public void forward_step2_handler(){
        log.info("GET /param/forward/step2");
    }




    // Redirect
    @GetMapping("/redirect/init")
    public String redirect_init_handler(Model model, RedirectAttributes redirectAttributes){
        log.info("GET /param/redirect/init...");
        model.addAttribute("init", "init_value");
        redirectAttributes.addAttribute("r_init","r_init_value"); // 쿼리 스트링으로 전달 -> 쿼리스트링을 받아야 하니까
        redirectAttributes.addFlashAttribute("r_init2", "r_init2_value");
        return "redirect:/param/redirect/step1"; // 새로운 요청을 하는 것이기 때문에 새로운 URL로 잡힙 (forward와 다르게)
    }
    @GetMapping("/redirect/step1")
    public void redirect_step1_handler(Model model, @RequestParam String r_init){
        log.info("GET /param/redirect/step1... r_init"+r_init);
        model.addAttribute("step1", "step1_value"); // 이 model만 보여짐 - 세션보다 좁은 범위
    }
    @GetMapping("/redirect/step2")
    public void redirect_step2_handler(){
        log.info("GET /param/redirect/step2");
    }



//    @RequestMapping("")
//    public void paramHandler_(){
//        log.info("");
//    }

}
