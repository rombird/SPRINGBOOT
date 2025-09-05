package com.example.demo.Domain.Common.Service;


import com.example.demo.Domain.Common.Entity.Memo;
import com.example.demo.Domain.Common.Repository.MemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class TxTestService {

    @Autowired
    private MemoRepository memoRepository;

    public void addMemo() throws Exception {
        log.info("TxTestService's addMemo ... ");
        Memo memo = Memo.builder()
                    .id(null)
                    .text("tx")
                    .writer("a")
                    .createAt(LocalDateTime.now())
                    .build();
        memoRepository.save(memo); // insert하고나서 insert된 id가 잡힘 -> 한 행 생기고 나면 null로 처리해야 새로운 insert된 값들이 생성
        memo.setId(null);
        memoRepository.save(memo);
        memo.setId(null);
        memoRepository.save(memo);
        memo.setId(null);
        throw new SQLException(); // 위의 코드까지는 실행됨(트랜잭션 처리하기 전이라) 
//        memoRepository.save(memo);
    }
    
    @Transactional(rollbackFor = SQLException.class, transactionManager ="jpaTransactionManager") // SQLException이 발생하는 모든 클래스는 rollback처리하겠다
    public void addMemoTx() throws Exception {
        log.info("TxTestService's addMemoTx ... ");
        Memo memo = Memo.builder()
                .id(null)
                .text("tx")
                .writer("a")
                .createAt(LocalDateTime.now())
                .build();
        memoRepository.save(memo); // insert하고나서 insert된 id가 잡힘 -> 한 행 생기고 나면 null로 처리해야 새로운 insert된 값들이 생성
        memo.setId(null);
        memoRepository.save(memo);
        memo.setId(null);
        memoRepository.save(memo);
        memo.setId(null);
        throw new SQLException(); // 위의 코드까지는 실행됨(트랜잭션 처리하기 전이라) 
//        memoRepository.save(memo);
    }
}
