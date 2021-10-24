package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(studentConfiguration.class)
@EnableConfigurationProperties(studentProperties.class)
public class StudentAutoConfiguration {

    @Autowired
    studentConfiguration properties;

    @Autowired
    studentConfiguration configuration;

    @Bean
    public Student creatStudent(){
        return new Student();
    }

}
