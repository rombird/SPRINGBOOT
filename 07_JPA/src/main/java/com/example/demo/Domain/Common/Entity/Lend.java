package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Entity
@Table(name="lend")  // 테이블명 book
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정 - 자동증가
    private Long id;

    // -동일한 책이 여러권 있다고 가정하고 다대 일 관계로 작업해보기
    // lend와 user 연결작업
    @ManyToOne(fetch = FetchType.EAGER) // 관계 매핑 -> 다 대 일 lend가 다(M), (fetch = FetchType.EAGER) - FETCH 전략 사용
    @JoinColumn(
            name = "username",
            foreignKey = @ForeignKey(
                    name="FK_LEND_USER",
                    foreignKeyDefinition = "FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE"
            )

    ) // 어떤 컬럼과 연결할 건지
    private User user; // lend table은 user의 기본 컬럼과 연결해서 사용

    // lend와 book 연결작업
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "bookCode",
            foreignKey = @ForeignKey(
                    name="FK_LEND_BOOK",
                    foreignKeyDefinition = "FOREIGN KEY (bookCode) REFERENCES book(bookCode) ON DELETE CASCADE ON UPDATE CASCADE"
            )
    )
    // 백틱제거 -> 참조 테이블명 바꾸고 괄호와 붙여쓰기 (book(bookCode))
    // FOREIGN KEY (bookCode) REFERENCES book(bookCode) ON DELETE CASCADE ON UPDATE CASCADE
    private Book book; // entity book

}
