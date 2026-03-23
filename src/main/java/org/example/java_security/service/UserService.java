package org.example.java_security.service;


import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserRequest;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.mapper.UserMapper;
import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(final UserRequest newUser) {

        if (userRepository.existsByUsername(newUser.username())) {
            throw new RuntimeException("User already exists");
        }
        if (userRepository.existsByEmail(newUser.email())) {
            throw new RuntimeException("User email already exists");
        }

        User user = userMapper.toEntity(newUser);
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(newUser.password()));
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userMapper.toResponse(userRepository.findByUsername(username));
    }

    public List<UserResponse> getUsersForCurrentUser(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return userRepository.findAll().stream()
                    .map(userMapper::toResponse)
                    .toList();
        } else {
            User user = userRepository.findByUsername(authentication.getName());
            return List.of(userMapper.toResponse(user));
        }
    }
}
