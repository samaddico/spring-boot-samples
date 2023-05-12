package com.samddico.playground;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBootMicrometerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicrometerApplication.class, args);
    }

    @Bean
    public MeterRegistry meterRegistry(){
        return new CompositeMeterRegistry();
    }
}
