package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface MemoService {
    boolean memoRegistration(MemoDto dto) throws Exception;

    // 트랜잭션 사용
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    Long memoRegistration2(MemoDto dto) throws Exception;
}

// MemoService의 이름을 MemoServiceImpl 로 바꿔준 다음 작업
// 리펙터링 -> 인터페이스 추출 -> 결합도 낮추는 방법(Controller -> Service연결시 결합도 낮추는 방법)
// Imple와 repositoy를 연결해서 중간에 공정을 하나 추가하는 작업이라고 생각
