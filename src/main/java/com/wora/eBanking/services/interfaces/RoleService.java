package com.wora.eBanking.services.interfaces;

import com.wora.eBanking.dtos.role.CreateRoleDTO;
import com.wora.eBanking.dtos.role.RoleDTO;
import com.wora.eBanking.dtos.role.UpdateRoleDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.entites.Role;
import com.wora.eBanking.services.GenericService;

public interface RoleService extends GenericService<CreateRoleDTO, UpdateRoleDTO, RoleDTO,Long> {
    RoleDTO update(UpdateRoleDTO dto, Long id);
    Role createDefault();
    RoleDTO createAdmin();
}
