package com.wora.eBanking.controllers;

import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


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
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
