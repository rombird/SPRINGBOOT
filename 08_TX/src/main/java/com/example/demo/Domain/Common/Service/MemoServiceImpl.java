package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dao.MemoDao;
import com.example.demo.Domain.Common.Dto.MemoDto;
import com.example.demo.Domain.Common.Entity.Memo;
import com.example.demo.Domain.Common.Repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class MemoServiceImpl implements MemoService {
    @Autowired
    private MemoDao memoDao;

    // memorepository 인터페이스 연결
    @Autowired
    private MemoRepository memoRepository;


    @Override
    public boolean memoRegistration(MemoDto dto) throws Exception {
        int result = memoDao.insert(dto);
        return result>0;
    }

    // 트랜잭션 사용
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    @Override
    public Long memoRegistration2(MemoDto dto) throws Exception {
        //dto -> entity
        Memo memo = Memo.builder()
                    .id(null)
                    .text(dto.getText())
                    .writer(dto.getWriter())
                    .createAt(LocalDateTime.now())
                    .build();
        memoRepository.save(memo); // 메모의 내용을 추가하는 작업
        return memo.getId(); // 추가된 행의 id값 반환받도록
    }


}
