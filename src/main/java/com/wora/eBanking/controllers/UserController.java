package com.wora.eBanking.controllers;

import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{username}")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        userService.updatePassword(username, passwordUpdateDTO);
        return ResponseEntity.ok("Password updated successfully");
    }

    @GetMapping("/public/notice")
    public ResponseEntity<String> getNotice() {
        return ResponseEntity.ok("Public notice accessible without authentication.");
    }

    @GetMapping("/user/myLoans")
    public ResponseEntity<String> getMyLoans() {
        return ResponseEntity.ok("Loans data for the authenticated user.");
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody CreateUserDTO user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/admin/manageUsers")
    public ResponseEntity<List<UserDTO>> manageUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/admin/deleteUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

}
