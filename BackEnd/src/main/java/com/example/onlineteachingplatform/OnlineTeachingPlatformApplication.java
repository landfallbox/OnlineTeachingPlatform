package com.example.onlineteachingplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Samoye
 */
@SpringBootApplication
public class OnlineTeachingPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineTeachingPlatformApplication.class, args);
    }

}
