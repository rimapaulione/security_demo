package org.example.java_security.config;

import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository repository, PasswordEncoder encoder) {
        return args -> {
            repository.save(new User(null, "user", encoder.encode("user"), "USER"));
            repository.save(new User(null, "admin", encoder.encode("admin"), "ADMIN"));
        };
    }
}
