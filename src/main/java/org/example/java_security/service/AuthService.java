package org.example.java_security.service;

import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserRequest;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.exception.UserAlreadyExistsException;
import org.example.java_security.mapper.UserMapper;
import org.example.java_security.model.Role;
import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(final UserRequest newUser) {

        if (userRepository.existsByUsername(newUser.username())) {
            throw new UserAlreadyExistsException("Username '" + newUser.username() + "' is already taken");
        }
        if (userRepository.existsByEmail(newUser.email())) {
            throw new UserAlreadyExistsException("Email '" + newUser.email() + "' is already registered");
        }

        User user = userMapper.toEntity(newUser);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(newUser.password()));
        return userMapper.toResponse(userRepository.save(user));
    }
}
