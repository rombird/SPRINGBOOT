package com.example.demo.Domain.Common.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MemoDto {
    // id 제한 해주기(양수값으로 10 ~ 65535까지만 가능하도록
//    @Min(value = 10, message = "ID는 10 이상의 값부터 시작합니다.")
//    @Max(value=665535, message = "ID의 최대숫자는 65535입니다.")
//    @NotNull(message="ID는 필수항목입니다.")
//    private Long id;
    // memorepository를 할경우 넣어주지 않아도 되기 때문에 주석처리

    @NotBlank(message = "TEXT를 입력하세요.") // String은 NotBlank로
    private String text;

    @NotBlank(message = "작성자를 입력하세요.")
    @Email(message = "example@example.com형식으로 입력해주세요.")
    private String writer;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  // format 안해도 출력이 되긴하지만 알아두기
//    @NotNull(message="날짜 정보를 선택하세요")
//    @Future(message="오늘날짜기준 이후 날짜를 입력하세요.")
//    private LocalDateTime createAt; // 시간
//
//    // 외부에서 입력한 값은 문자열이고 여기서 전달받은 값은 년, 월, 일 형태의 local date 타입
//    private LocalDate data_test;

}

