package com.example.demo.Domain.Common.Repository;


import com.example.demo.Domain.Common.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> { // Entity명, pk 자료형

    // JPQL
    @Query("SELECT u FROM User as u where u.role=?1") // SQL Query문을 사용
    List<User> selectAllByRole(String role); // role의 내용에 일치하는 user 전부 꺼내오기
    // SELECT * FROM testdb.user where role='ROLE_USER'
    // SELECT u FROM User as u where u.role=?1 // 몇번째 물음표인지 숫자와 함께 사용 가능 * 대신 user의 u로 사용
    // User : 대문자
    // as u : 별칭 지정 User 를 u라고 사용하겠다

    // 파라미터 2개 사용
    // SELECT * FROM user where role='ROLE_USER' and password='1111';
    // SELECT u From User as u where u.role=?1 and u.password=2;

    @Query("SELECT u From User as u where u.role=?1 and u.password=?2")
    List<User> selectAllByRoleAndPwd(String role, String password); // 파라미터 순서에 맞게 
    
    // 변수처리하는 방법
    @Query("SELECT u FROM User as u where u.role=:role") // 내부 변수명과 일치
    List<User> selectAllByRole_2(@Param("role") String a); // 변수명과 매칭 @Param("role일치"), String role 대신 아무변수나

    // 포함문자열 검색
    // SELECT u FROM User as u where u.username like concat('%', 'user', '%')
    @Query("SELECT u FROM User as u where u.username like concat('%', :user, '%')")
    List<User> selectAllLikeUsername(@Param("user") String username);
    
}
