package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// MemoDao -> Repository :
// <엔티티, PK 자료형>
@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // repository를 통해 복잡한 쿼리를 사용하는 방법 2가지
    // 메서드 명명법(메서드 이름을 잘 조합하여 SQL문 작성하지 않고도 사용가능)
    // JPQL (SQL문 직접)
}
