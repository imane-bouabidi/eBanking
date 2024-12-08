package com.wora.eBanking.services.interfaces;

import com.wora.eBanking.dtos.PasswordUpdateDTO;
import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UpdateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.services.GenericService;

public interface UserService extends GenericService<CreateUserDTO, UpdateUserDTO, UserDTO,Long> {
    void updatePassword(String username, PasswordUpdateDTO passwordUpdateDTO);
    UserDTO findById(Long id);
}
