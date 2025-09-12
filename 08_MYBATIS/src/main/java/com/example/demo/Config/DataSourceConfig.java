package com.example.demo.Config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    
    // bean 생성작업
//    @Bean
//    public DataSource dataSource2(){
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1234");
//
//        dataSource.setInitialSize(5); // 초기 연결개수
//        dataSource.setMaxTotal(10); // 최대 연결 개수
//        dataSource.setMaxIdle(8); // 최대 유휴 연결 수
//        dataSource.setMinIdle(3); // 최소 유휴 연결 수
//
//        return dataSource; // public void가 아니기 때문에 return 꼭 해주기!
//    }

    // Bean명 잘 확인 - dataSource3 ***
    @Bean
    public HikariDataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
}