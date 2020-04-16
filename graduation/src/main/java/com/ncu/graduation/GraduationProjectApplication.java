package com.ncu.graduation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@MapperScan("com.ncu.graduation.mapper")
public class GraduationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationProjectApplication.class, args);
    }

}
