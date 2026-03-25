package org.example.java_security.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.event.UserDeleteEvent;
import org.example.java_security.event.UserRoleChangeEvent;
import org.example.java_security.exception.UserNotFoundException;
import org.example.java_security.mapper.UserMapper;
import org.example.java_security.dto.RoleUpdateRequest;
import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public UserResponse changeUserRole(Long id, RoleUpdateRequest newRole, Authentication authentication) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        checkNotSelf(authentication, user);

        publisher.publishEvent(new UserRoleChangeEvent(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                newRole.role(),
                authentication.getName(),
                LocalDateTime.now()
        ));

        user.setRole(newRole.role());
        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id, Authentication authentication) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        checkNotSelf(authentication, user);
        userRepository.delete(user);
        publisher.publishEvent(new UserDeleteEvent(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                authentication.getName(),
                LocalDateTime.now()
        ));
    }

    private void checkNotSelf(Authentication authentication, User user) {
        if (user.getUsername().equals(authentication.getName())) {
            throw new IllegalArgumentException("Cannot perform this action on yourself");
        }
    }
}
