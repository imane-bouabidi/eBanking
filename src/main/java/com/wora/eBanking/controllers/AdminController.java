    package com.wora.eBanking.controllers;

    import com.wora.eBanking.dtos.role.CreateRoleDTO;
    import com.wora.eBanking.dtos.user.UserDTO;
    import com.wora.eBanking.services.interfaces.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;


    @RestController
    @RequestMapping("/api/admin")
    @RequiredArgsConstructor
    public class AdminController {

        private final UserService userService;

//        @GetMapping("/manageUsers")
//        public ResponseEntity<List<UserDTO>> manageUsers() {
//            return ResponseEntity.ok(userService.findAll());
//        }

        @DeleteMapping("/deleteUser/{userId}")
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

//        @GetMapping
////        @PreAuthorize("hasRole('ADMIN')")
//        public ResponseEntity<RoleDTO> createRole() {
//            return ResponseEntity.ok(userService.findAll());
//        }
    }
