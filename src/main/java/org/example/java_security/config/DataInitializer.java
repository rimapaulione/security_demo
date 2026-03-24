package org.example.java_security.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository repository, PasswordEncoder encoder,
                           ObjectMapper objectMapper) {
        return args -> {
            if (repository.count() > 0) return;

            InputStream input = getClass().getResourceAsStream("/data/users.json");
            List<User> users = objectMapper.readValue(input, new
                    TypeReference<List<User>>() {
                    });

            users.forEach(user ->
                    user.setPassword(encoder.encode(user.getPassword())));
            repository.saveAll(users);
        };
    }
}
