package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendRepositoryTest {

    // LendREpositroyTest에서 미리 lend, user, book Repository 연결 시켜둬야함
    // ??
    @Autowired
    private LendRepository lendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1(){
        // SELECT
//        List<Lend> list = lendRepository.findAll(); // 반환자료형 list <lend>
//        list.forEach(System.out::println);

        // INSERT
        // user, book 을 조회해서 가져온 것에서 포함해서 lend에 넣어주는 것
        User user = userRepository.findById("user1").get(); // null 체크 안하고 하는 것
        Book book = bookRepository.findById(1L).get(); //
        Lend lend = Lend.builder() // id는 무시하고 작업
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend); // autoincrement 된  id값 저장되어있음


    }
}