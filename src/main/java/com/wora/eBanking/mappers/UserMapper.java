package com.wora.eBanking.mappers;

import com.wora.eBanking.dtos.user.CreateUserDTO;
import com.wora.eBanking.dtos.user.UpdateUserDTO;
import com.wora.eBanking.dtos.user.UserDTO;
import com.wora.eBanking.entites.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(CreateUserDTO userDTO);
    User toEntity(UpdateUserDTO userDTO);
    UserDTO toDTO(User user);
    List<UserDTO> toListDTO(List<User> users);
}
