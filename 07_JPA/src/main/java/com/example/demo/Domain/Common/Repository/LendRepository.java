package com.example.demo.Domain.Common.Repository;


import com.example.demo.Domain.Common.Entity.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendRepository extends JpaRepository<Lend, Long> {

    // SELECT * FROM user u INNER JOIN lend l ON u.username=l.username WHERE u.username='user1';
    // SELECT l FROM Lend AS l JOIN FETCH
    // Lend를 조회하면 user의 username,  book의 bookCode 가 있기 때문에 lend 안에는 user 정보 포함되어있음
    // l.user - Lend Entity에 있는 user, l.user.username (lend안에 있는 user의 username이 받아오는 username과 일치)
    @Query("SELECT l FROM Lend AS l JOIN FETCH l.user WHERE l.user.username=:username")
    List<Lend> findAllLendsByUser(@Param("username") String username); // 유저정보를 받아서 유저가 도서를 얼마나 대여하고 있는지에 대한 정보
    // JPQL과 연결되어있는 :username과 Param으로 연결해놓은 username

    @Query("SELECT l FROM Lend AS l JOIN FETCH l.book WHERE l.book.bookName=:bookName")
    List<Lend> findAllLendsByBook(@Param("bookName") String bookName);

}
