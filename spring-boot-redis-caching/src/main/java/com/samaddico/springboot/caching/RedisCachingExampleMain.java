package com.samaddico.springboot.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching()
@SpringBootApplication
public class RedisCachingExampleMain {

    public static void main(String[] args) {
        SpringApplication.run(RedisCachingExampleMain.class, args);
    }

}
