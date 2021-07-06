package com.neutech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.neutech.mapper")
public class ShopServerDdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServerDdApplication.class, args);
    }

}
