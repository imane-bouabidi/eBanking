package com.wora.eBanking.controllers;

import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicAccessController {
    private final UserService userService;

    @GetMapping("/notices")
    public ResponseEntity<String> getNotice() {
        return ResponseEntity.ok("Public notice accessible without authentication.");
    }

    @GetMapping("/contact")
    public ResponseEntity<String> Contact() {
        return ResponseEntity.ok("contact support");
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody CreateUserDTO user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
