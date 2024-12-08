package com.wora.eBanking.services.Impl;

import com.wora.eBanking.dtos.role.CreateRoleDTO;
import com.wora.eBanking.dtos.role.RoleDTO;
import com.wora.eBanking.dtos.role.UpdateRoleDTO;
import com.wora.eBanking.entites.Role;
import com.wora.eBanking.exceptions.EntityNotFoundException;
import com.wora.eBanking.mappers.RoleMapper;
import com.wora.eBanking.repositories.RoleRepository;
import com.wora.eBanking.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDTO save(CreateRoleDTO createRoleDto) {
        Role role = roleMapper.toEntity(createRoleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDTO(savedRole);
    }

    public RoleDTO update(UpdateRoleDTO dto, Long id) {
        return null;
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
