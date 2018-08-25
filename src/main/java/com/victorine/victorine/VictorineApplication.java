package com.victorine.victorine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class VictorineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VictorineApplication.class, args);
       // SpringApplication.run(RefreshAnswer.class, args);
    }
}
