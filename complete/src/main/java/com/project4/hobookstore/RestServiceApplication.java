package com.project4.hobookstore;


import java.util.Collections;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    }
}
