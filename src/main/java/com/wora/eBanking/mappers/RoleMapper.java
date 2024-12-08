package com.wora.eBanking.mappers;

import com.wora.eBanking.dtos.role.CreateRoleDTO;
import com.wora.eBanking.dtos.role.RoleDTO;
import com.wora.eBanking.dtos.role.UpdateRoleDTO;
import com.wora.eBanking.entites.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(CreateRoleDTO userDTO);
    Role toEntity(UpdateRoleDTO userDTO);
    RoleDTO toDTO(Role role);
    List<RoleDTO> toListDTO(List<Role> roles);
}