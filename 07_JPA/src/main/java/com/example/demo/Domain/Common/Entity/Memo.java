package com.example.demo.Domain.Common.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // entity라고 명시해주면 테이블 생성함을 알 수 있음
@Table(name="memo")
@Data
@NoArgsConstructor // 디폴트 생성자
@AllArgsConstructor // 모든인자 생성자
@Builder 
public class Memo {
    
    // 컬럼 만들기
    @Id // 프라이머리 키로 만들어주기 위해
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement (ID값 자동 생성) -> AI에 체크
    private Long id;
    @Column(length=1024) // 컬럼의 속성추가
    private String text;
    @Column(length=100,nullable = false) // false : 무조건 데이터가 들어오도록 -> NotNull 체크되어짐
    private String writer;
    private LocalDateTime createAt;

    // DB에 CRUD를 하기 위한 작업 필요
    
}
