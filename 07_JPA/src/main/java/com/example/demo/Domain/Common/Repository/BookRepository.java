package com.example.demo.Domain.Common.Repository;


import com.example.demo.Domain.Common.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Book Entity -> BookRepository -> RepositoryTest(ctrl+shift+T)
// 애노테이션 추가 -> extends 상속 추가
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    // 함수명명법  test
    List<Book> findByBookName(String bookName); // 이름에 대한 규칙을 잘 지켰다면 여기에 대한 구현을 내부에서 알아서 만들어줌 Entity의 bookName을 그대로 가져와 앞글자만 대문자로
    List<Book> findByPublisher(String publisher);
    Book findByIsbn(String isbn); // 만약 유일한 항목이라면 단건 조회형태로 가능
    List<Book> findByBookNameAndPublisher(String bookName, String publisher); // AND 연산자
    List<Book> findByBookNameContains(String keyword); // 포함문자 - select * from memo where bookName like '%?%' 와 같은 형식


}
