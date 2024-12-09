package com.wora.eBanking.services.Impl;

import com.wora.eBanking.dtos.role.CreateRoleDTO;
import com.wora.eBanking.dtos.role.RoleDTO;
import com.wora.eBanking.dtos.role.UpdateRoleDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.entites.Role;
import com.wora.eBanking.entites.User;
import com.wora.eBanking.exceptions.EntityNotFoundException;
import com.wora.eBanking.mappers.RoleMapper;
import com.wora.eBanking.mappers.UserMapper;
import com.wora.eBanking.mappers.UserMapperImpl;
import com.wora.eBanking.repositories.RoleRepository;
import com.wora.eBanking.repositories.UserRepository;
import com.wora.eBanking.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public RoleDTO save(CreateRoleDTO createRoleDto) {
        Role role = roleMapper.toEntity(createRoleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDTO(savedRole);
    }

    public Role createDefault() {
        CreateRoleDTO roleDTO = new CreateRoleDTO("ROLE_USER");
        Role role = roleMapper.toEntity(roleDTO);
        return roleRepository.save(role);
    }

    public RoleDTO createAdmin() {
        CreateRoleDTO roleDTO = new CreateRoleDTO("ROLE_ADMIN");
        Role role = roleMapper.toEntity(roleDTO);
        return roleMapper.toDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO update(UpdateRoleDTO dto, Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
        Role role = roleRepository.findByName(dto.getRoleName()).orElseThrow(()-> new EntityNotFoundException("Role not found"));

        user.setRole(role);
        userRepository.save(user);
        return roleMapper.toDTO(role);
    }
    public RoleDTO findByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(()-> new EntityNotFoundException("Role not found"));
        return roleMapper.toDTO(role);
    }
    public List<RoleDTO> findAll() {
        return null;
    }
    public void delete(Long id) {
        roleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Role not found"));
        roleRepository.deleteById(id);
    }


}
