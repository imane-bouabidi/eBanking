package com.wora.eBanking.controllers;

import com.wora.eBanking.dtos.PasswordUpdateDTO;
import com.wora.eBanking.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{username}")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        userService.updatePassword(username, passwordUpdateDTO);
        return ResponseEntity.ok("Password updated successfully");
    }

    @GetMapping("/myLoans")
    public ResponseEntity<String> getMyLoans() {
        return ResponseEntity.ok("Loans data for the authenticated user.");
    }

    @GetMapping("/myCards")
    public ResponseEntity<String> getMyCards() {
        return ResponseEntity.ok("Cards.");
    }

    @GetMapping("/myAccount")
    public ResponseEntity<String> getMyAccount() {
        return ResponseEntity.ok("Account");
    }

    @GetMapping("/myBalance")
    public ResponseEntity<String> getMyBalance() {
        return ResponseEntity.ok("Balance");
    }


}
