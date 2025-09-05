package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class MemoServiceTest {

    //service 연결
    @Autowired
    private MemoService memoService;

    @Test
    public void t1() throws Exception{
        MemoDto dto = new MemoDto(11L, "", "a@a.com", LocalDateTime.now(), null);
        memoService.memoRegistration(dto);  // dto로 위의 값지정한 것 던져주기
    }
}