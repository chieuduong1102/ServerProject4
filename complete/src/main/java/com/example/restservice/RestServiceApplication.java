package com.example.restservice;

import org.modelmapper.ModelMapper;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestServiceApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8088"));
        app.run(args);
        //run file này nhé
        // tạo model => tạo jpa controller => tạo DAO => tạo API.java
        // OKay . B1: ket noi db - MySQL nhjé
        //B2: tạo entiti từ db - package model nhé.
        //B3: JPAControllr
        //B4: UserDAO có mấy cái hàm truy vấn ấy.
        //B5. Tạo API.java
        // @Getmapping : method GET.
    }
}
