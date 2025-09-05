package com.example.demo.Domain.Common.Dao;


import com.example.demo.Domain.Common.Dto.MemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 함수명을 직관적으로 insert, selecet 등등으로 해주기


// 컨텍스영역에다가 빈으로 저장시켜둠
@Repository
public class MemoDao { // MemoDao에 커서 갖다대고 -> ctrl + shift + T -> 테스트 생성

    @Autowired
    private DataSource dataSource3; // 데이터소스를 적절하게 꺼내서 CRUD 가능

    public int insert(MemoDto dto) throws SQLException { // Dao -> dto로 받을 것
        Connection conn = dataSource3.getConnection(); // 예외던져주기 - 데이터소스 연결
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null, ?, ?, now())"); // 던질 준비
        // dto로 부터 전달된 값으로 바꿔줘야함(값을 직접 지정하는 것이 X)
        pstmt.setString(1, dto.getText());
        pstmt.setString(2, dto.getWriter());
        int result = pstmt.executeUpdate();
        return result; // 추가한 행의 개수를 return
    }




}
