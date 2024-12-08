package com.wora.eBanking.services.Impl;

import com.wora.eBanking.dtos.PasswordUpdateDTO;
import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UpdateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.entites.Role;
import com.wora.eBanking.entites.User;
import com.wora.eBanking.exceptions.PasswordException;
import com.wora.eBanking.exceptions.UsernameAlreadyExistsException;
import com.wora.eBanking.mappers.UserMapper;
import com.wora.eBanking.repositories.RoleRepository;
import com.wora.eBanking.repositories.UserRepository;
import com.wora.eBanking.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserDTO save(CreateUserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("User already exists.");
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found."));
        User user = userMapper.toEntity(userDTO);
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(role);

        return userMapper.toDTO(userRepository.save(user));
    }


    public void updatePassword(String username, PasswordUpdateDTO passwordUpdateDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameAlreadyExistsException("User not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals(username)) {
            throw new PasswordException("Action inaccessible");
        }

        if (!passwordEncoder.matches(passwordUpdateDTO.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
        userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        return userMapper.toListDTO(userRepository.findAll());
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

    public UserDTO update(UpdateUserDTO dto, Long id){
        return null;
    }
    public UserDTO findById(Long id){
        User user  = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }
}
