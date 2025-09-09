package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("-- 기본 CRUD 확인 --")
    @Test
    public void t1(){
        // BookEntity를 만드는 작업
        Book book = Book.builder()
                .bookCode(1L)
                .bookName("JAVA의 정석") // insert 한번했으면 내용바꿔서 update문 실행
                .publisher("이지퍼블리싱")
                .isbn("2222-1111")
                .build();

        // INSERT
        // bookRepository.save(book); // book에 넣은 내용 저장

        // UPDATE - INSERT 후 INSERT문 아래 코드 주석처리 -> bookEntity에서 bookCode를 제외한 나머지 컬럼 변경 후 UPDATE되는지 확인
        // bookRepository.save(book);

        // DELETE
        // bookRepository.deleteById(1L); // deleteById : Id를 기준으로 삭제

        // SELECT
//        Optional<Book> bookOptional = bookRepository.findById(1L); // 단건조회(지정한 ID만 조회)
//        Book rBook = null;
//        if(bookOptional.isPresent()){
//            rBook = bookOptional.get();
//            System.out.println(rBook);
//        }

        // SELECTALL
        List<Book> list = bookRepository.findAll();
        list.forEach(System.out::println);
        // 페이징 처리 필요함
    }

    @DisplayName("-- 함수명명법 TEST --")
    @Test
    public void t2(){
        // BookRepository에서 함수 명명법으로 생성후 사용가능
//        List<Book> list = bookRepository.findByBookName("a");
//        list.forEach(System.out::println);
//
//        List<Book> list2 = bookRepository.findByPublisher("c3");
//        list2.forEach(System.out::println);

        List<Book> list = bookRepository.findByBookNameContains("d"); // bookName에 d를 포함하는
        list.forEach(System.out::println);

    }
}