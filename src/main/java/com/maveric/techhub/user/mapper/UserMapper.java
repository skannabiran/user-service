package com.maveric.techhub.user.mapper;

import com.maveric.techhub.user.entity.User;
import com.maveric.techhub.user.model.UpdateUserRequest;
import com.maveric.techhub.user.model.UserDTO;
import com.maveric.techhub.user.model.CreateUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserRequest request);
    User toEntity(UpdateUserRequest request);
    UserDTO toDTO(User user);

}
