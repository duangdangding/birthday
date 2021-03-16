package com.lsh.birthday;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lsh.birthday.mapper")
public class BirthdayApplication {
    private static final Logger logger = LoggerFactory.getLogger(BirthdayApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BirthdayApplication.class, args);
    }

}
