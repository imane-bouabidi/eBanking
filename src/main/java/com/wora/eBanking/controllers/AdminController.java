    package com.wora.eBanking.controllers;

    import com.wora.eBanking.dtos.role.RoleDTO;
    import com.wora.eBanking.dtos.role.UpdateRoleDTO;
    import com.wora.eBanking.dtos.user.UserDTO;
    import com.wora.eBanking.services.interfaces.RoleService;
    import com.wora.eBanking.services.interfaces.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/admin")
    @RequiredArgsConstructor
    public class AdminController {

        private final UserService userService;
        private final RoleService roleService;


        @DeleteMapping("/deleteUser/{userId}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
            userService.delete(userId);
            return ResponseEntity.ok("User deleted successfully.");
        }

        @GetMapping("/all")
        public ResponseEntity<List<UserDTO>> getAllUsers() {
            return ResponseEntity.ok(userService.findAll());
        }

        @PostMapping("creerAdminRole")
        public ResponseEntity<RoleDTO> createRole() {
            return ResponseEntity.ok(roleService.createAdmin());
        }


        @PutMapping("/updateRole/{id}")
        public ResponseEntity<String> updateRole(@RequestBody UpdateRoleDTO dto, @PathVariable Long id) {
            roleService.update(dto, id);
            return ResponseEntity.ok("role updated successfully");
        }
    }
