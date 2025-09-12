package com.example.demo.Config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MybatisConfigTest {

    //
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void t1(){
        assertNotNull(sqlSessionFactory); // null 체크 -> sessionFactory Bean이 잘 생성되었는지 확인 -> 실행
        // sqlsessionFactory에서 session을 꺼내오는 작업
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection conn = sqlSession.getConnection(); // conn 연결
        assertNotNull(conn); // -> 실행

    }



}