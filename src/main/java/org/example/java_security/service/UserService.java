package org.example.java_security.service;


import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.exception.UserNotFoundException;
import org.example.java_security.mapper.UserMapper;
import org.example.java_security.model.Role;
import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Cacheable(value = "currentUser", key = "#authentication.name")
    public UserResponse getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userMapper.toResponse(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User '" + username + "' not found")));
    }

    @Cacheable(value = "users", key = "#authentication.name")
    public List<UserResponse> getUsersForCurrentUser(Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + Role.ADMIN.name()));

        if (isAdmin) {
            return userRepository.findAll().stream()
                    .map(userMapper::toResponse)
                    .toList();
        } else {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new UserNotFoundException("User '" + authentication.getName() + "' not found"));

            return List.of(userMapper.toResponse(user));
        }
    }
}
