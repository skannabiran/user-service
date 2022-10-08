package com.maveric.techhub.user.mapper;

import com.maveric.techhub.user.entity.User;
import com.maveric.techhub.user.model.UserDTO;
import com.maveric.techhub.user.model.UserRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);
    UserDTO toDTO(User user);

    List<UserDTO> toDTO(List<User> users);

}
