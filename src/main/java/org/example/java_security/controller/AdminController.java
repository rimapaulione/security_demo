package org.example.java_security.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.dto.RoleUpdateRequest;
import org.example.java_security.service.AdminService;
import org.example.java_security.service.RateLimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final RateLimitService rateLimitService;

    @PutMapping("/{id}/role")
    public ResponseEntity<UserResponse> changeUserRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateRequest newRole,
            Authentication authentication) {
        rateLimitService.checkAdminActionLimit(authentication.getName());
        return ResponseEntity.ok(adminService.changeUserRole(id, newRole,
                authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            Authentication authentication) {
        rateLimitService.checkAdminActionLimit(authentication.getName());
        adminService.deleteUser(id, authentication);
        return ResponseEntity.noContent().build();
    }

}
