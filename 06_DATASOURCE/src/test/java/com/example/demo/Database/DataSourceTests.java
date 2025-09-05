package com.example.demo.Database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DataSourceTests {

    // 01 방법
//    @Autowired
//    private DataSource dataSource;
//
//    @Test
//    public void t1() throws SQLException {
//        assertNotNull(dataSource);
//        System.out.println(dataSource); // HikariDataSource (null) : 일반적으로 많이 쓰는 커넥션풀
//        Connection conn = dataSource.getConnection(); // 예외던져주기 - 데이터소스 연결
//        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?, ?, ?, ?)"); // 던질 준비
//        pstmt.setLong(1, 1L);
//        pstmt.setString(2, "내용내용");
//        pstmt.setString(3, "test@naver.com");
//        pstmt.setString(4, LocalDateTime.now().toString());
//        int result = pstmt.executeUpdate();
//    }

    // 02 연결방법 - DataSourceConfig에서 만든 dataSource2와 연결
    @Autowired
    private DataSource dataSource2; // 이름 정확히 연결 - dataSource2

    @Test
    public void test2() throws SQLException {
        assertNotNull(dataSource2);
        System.out.println(dataSource2); // HikariDataSource (null) : 일반적으로 많이 쓰는 커넥션풀
        Connection conn = dataSource2.getConnection(); // 예외던져주기 - 데이터소스 연결
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?, ?, ?, ?)"); // 던질 준비
        pstmt.setLong(1, 2L);
        pstmt.setString(2, "내용내용");
        pstmt.setString(3, "test2@naver.com");
        pstmt.setString(4, LocalDateTime.now().toString());
        int result = pstmt.executeUpdate();

    }
    
    // Hikari 직접만들어서 DataSource로도 가져올 수 있음
    // But 직접만들어서 문제가 발생
    // --> 해결 : Hikari 하기위해 t1 주석처리, application.properties 에서 DATASOURCE부분도 주석처리(히카리가 중복처리되서)

    @Autowired
    private DataSource dataSource3; // 업캐스팅

    @Test
    public void test3() throws Exception {
        assertNotNull(dataSource3);
        System.out.println(dataSource3); // HikariDataSource (null) : 일반적으로 많이 쓰는 커넥션풀
        Connection conn = dataSource3.getConnection(); // 예외던져주기 - 데이터소스 연결
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?, ?, ?, ?)"); // 던질 준비
        pstmt.setLong(1, 3L);
        pstmt.setString(2, "내용내용");
        pstmt.setString(3, "test3@naver.com");
        pstmt.setString(4, LocalDateTime.now().toString());
        int result = pstmt.executeUpdate();
    }


}
