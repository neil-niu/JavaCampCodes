package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(schoolConfiguration.class)
@EnableConfigurationProperties(schoolProperties.class)
public class schoolAutoConfiguration {

    @Autowired
    schoolProperties properties;

    @Autowired
    schoolConfiguration configuration;

    @Bean
    public School creatSchool(){
        return new School();
    }

}
