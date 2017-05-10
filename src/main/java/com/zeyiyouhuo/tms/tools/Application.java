package com.zeyiyouhuo.tms.tools;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

/**
 * Created by dave on 17/5/5.
 */
@SpringBootApplication
public class Application {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

