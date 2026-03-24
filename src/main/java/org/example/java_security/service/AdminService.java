package org.example.java_security.service;


import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.exception.UserNotFoundException;
import org.example.java_security.mapper.UserMapper;
import org.example.java_security.dto.RoleUpdateRequest;
import org.example.java_security.model.User;
import org.example.java_security.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserResponse changeUserRole(Long id, RoleUpdateRequest newRole, Authentication authentication) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        checkNotSelf(authentication, user);

        user.setRole(newRole.role());
        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id, Authentication authentication) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        checkNotSelf(authentication, user);
        userRepository.delete(user);
    }

    private void checkNotSelf(Authentication authentication, User user) {
        if (user.getUsername().equals(authentication.getName())) {
            throw new IllegalArgumentException("Cannot perform this action on yourself");
        }
    }
}
