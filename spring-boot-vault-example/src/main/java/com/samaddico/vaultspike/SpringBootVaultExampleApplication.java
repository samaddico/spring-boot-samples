package com.samaddico.vaultspike;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringBootVaultExampleApplication {

    @Value("${api.token}")
    private String token;
    @Value("${api.key}")
    private String key;

    @GetMapping("/")
    public Object home() {
        Map<String, String> authData = new HashMap<>();
        authData.put("key", key);
        authData.put("token", token);
        return authData;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVaultExampleApplication.class, args);
    }

}
