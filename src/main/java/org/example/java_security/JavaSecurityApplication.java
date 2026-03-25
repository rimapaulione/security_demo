package org.example.java_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JavaSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSecurityApplication.class, args);
    }
}
