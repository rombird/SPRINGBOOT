package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
//        User user = userRepository.findById("user1").get(); // null 체크 안하고 하는 것
//        Book book = bookRepository.findById(1L).get(); //
//        Lend lend = Lend.builder() // id는 무시하고 작업
//                .id(null)
//                .user(user)
//                .book(book)
//                .build();
//        lendRepository.save(lend); // autoincrement 된  id값 저장되어있음

    }

    @Test
    public void t2(){
        // INSERT
        // lend 테이블에서 id는 자동증가임을 기억!, 값을 넣는 거기때문에 한 번 넣고 나면 꼭 주석처리하고 실행

        // user1 bookCode 1L 대여
//        User user1 = userRepository.findById("user1").get();
//        Book book1 = bookRepository.findById(1L).get();
//        Lend lend1 = new Lend();
//        lend1.setBook(book1);
//        lend1.setUser(user1);
//        lendRepository.save(lend1);

        // user1 bookCode 2L 대여
//        Book book2 = bookRepository.findById(2L).get();
//        Lend lend2 = new Lend();
//        lend2.setBook(book2);
//        lend2.setUser(user1);
//        lendRepository.save(lend2);

        // user2 bookCode 3L 대여
//        User user2 = userRepository.findById("user2").get();
//        Book book3 = bookRepository.findById(3L).get();
//        Lend lend3 = new Lend();
//        lend3.setBook(book3);
//        lend3.setUser(user2);
//        lendRepository.save(lend3);

        // user3 bookCode 4L 대여
//        User user3 = userRepository.findById("user3").get();
//        Book book4 = bookRepository.findById(4L).get();
//        Lend lend4 = new Lend();
//        lend4.setBook(book4);
//        lend4.setUser(user3);
//        lendRepository.save(lend4);

        // t1과 같은 코드(안에 값을 일일이 넣어가며 변경해줬음)
//        User user = userRepository.findById("user3").get();
//        Book book = bookRepository.findById(4L).get();
//        Lend lend = Lend.builder().id(null).user(user).book(book).build();
//        lendRepository.save(lend);

    }

    @Test
    public void t3(){
//        List<Lend> list = lendRepository.findAllLendsByUser("user1");
//        list.forEach(System.out::println);

        List<Lend> list = lendRepository.findAllLendsByBook("TEST_BOOK");
        list.forEach(System.out::println);

    }

    @Test
    @Transactional(rollbackFor=Exception.class) // import : springframework로 클래스 선택
    public void t4(){
        // Eager 설정 / LAZY 설정 차이 확인 (Lend Entity에서 옵션 변경)
        System.out.println("--------------FETCH TEST START----------------");
        // 조회하는 시점 잡기
        Optional<Lend> lendOptional = lendRepository.findById(5L); // lend table에 존재하는 id값으로 찾을 것
        System.out.println("--------------lendRepository.findById(5L) invoke!!");
        // lend 꺼내주는 작업
        Lend lend = lendOptional.get();

        System.out.println("--------------lendOptional.get() invoke!!");

        User user = lend.getUser();         // lend 안에서 user를 꺼내는 작업
        System.out.println(user);           // 보는 작업도 넣어주기
        System.out.println("--------------lend.getUser() invoke!!");
        Book book = lend.getBook();        //
        System.out.println(book);
        System.out.println("--------------lend.getBook() invoke!!");
        System.out.println("--------------FETCH TEST END----------------");
    }


}