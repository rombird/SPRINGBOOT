package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")  // 테이블명 book
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    // 컬럼 지정
    @Id
    private Long bookCode;
//    @Column(name="bookname") // 실제 DB에서 컬럼명을 다르게 지정하고 싶을 때
    private String bookName;
    private String publisher;
    private String isbn;

}
