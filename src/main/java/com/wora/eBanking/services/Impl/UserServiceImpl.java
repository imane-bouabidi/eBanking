package com.wora.eBanking.services.Impl;

import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UpdateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.entites.User;
import com.wora.eBanking.mappers.UserMapper;
import com.wora.eBanking.repositories.UserRepository;
import com.wora.eBanking.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO save(CreateUserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> findAll() {
        return userMapper.toListDTO(userRepository.findAll());
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDTO update(UpdateUserDTO dto, Long id){
        return null;
    }
    public UserDTO findById(Long id){
        return null;
    }
}
