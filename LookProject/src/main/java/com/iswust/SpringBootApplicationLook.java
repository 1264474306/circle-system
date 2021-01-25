package com.iswust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.iswust.dao")
public class SpringBootApplicationLook {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationLook.class);
    }
}
