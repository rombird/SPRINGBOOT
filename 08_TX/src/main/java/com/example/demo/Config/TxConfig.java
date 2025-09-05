package com.example.demo.Config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class TxConfig {

    @Autowired
    private DataSource dataSource;
    // 기본 Tx(Mybatis 용)


    // JPA Tx
    @Bean(name="jpaTransactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) { // entitymanagerfactory가 알아서 만들어지게되는데
        JpaTransactionManager transactionManager = new JpaTransactionManager();                     //JpaConfig에서 설정해줬음 <- 의존주의를 받는다고 보면 된다. 이렇게 만들어진 것에 JpaTranscationManager 생성
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
