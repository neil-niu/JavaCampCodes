package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }



    // ==== 测试自动配置 ====
    @Autowired
    School school;

    @Autowired
    Student student100;

    @Bean
    public void printInfo(){
        school.ding();
    }

}
