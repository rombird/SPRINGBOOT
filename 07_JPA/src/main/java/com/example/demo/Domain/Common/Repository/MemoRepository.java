package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Long> {
    //매서드명명법
    //JPQL(SQL문직접)

    // 함수 명명법 - Memo Text 키워드를 포함하고 있는, 페이징처리를 하기위한 전달자
    Page<Memo> findByTextContaining(String keyword, Pageable pageable);
}
