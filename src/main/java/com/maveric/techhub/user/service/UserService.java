package com.maveric.techhub.user.service;

import com.maveric.techhub.user.repository.UserRepository;
import com.maveric.techhub.user.entity.User;
import com.maveric.techhub.user.exception.EntityNotFoundException;
import com.maveric.techhub.user.mapper.UserMapper;
import com.maveric.techhub.user.model.UpdateUserRequest;
import com.maveric.techhub.user.model.UserDTO;
import com.maveric.techhub.user.model.CreateUserRequest;
import com.maveric.techhub.user.model.UserResponse;
import com.maveric.techhub.user.util.ServiceConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponse> createUser(CreateUserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(userRequest.getEmail());
        if (!optionalUser.isPresent()) {
            User user = userRepository.save(userMapper.toEntity(userRequest));
            UserDTO userDTO = userMapper.toDTO(user);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(UserResponse.builder().response(userDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.EMAIL_ALREADY_EXISTS, userRequest.getEmail()));
    }

    public ResponseEntity<UserResponse> getUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = userMapper.toDTO(user);
            return ResponseEntity.status(HttpStatus.OK).
                    body(UserResponse.builder().response(userDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public ResponseEntity<UserResponse> updateUser(UpdateUserRequest userRequest) {
        Optional<User> optionalUserDB = userRepository.findById(userRequest.getId());
        if (optionalUserDB.isPresent()) {
            User userDB = optionalUserDB.get();
            if (!userDB.getEmail().equalsIgnoreCase(userRequest.getEmail())) {
                Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(userRequest.getEmail());
                if (optionalUser.isPresent()) {
                    throw new EntityNotFoundException(String.format(ServiceConstants.EMAIL_ALREADY_EXISTS, userRequest.getEmail()));
                }
            } else {
                User user = userRepository.save(userMapper.toEntity(userRequest));
                UserDTO userDTO = userMapper.toDTO(user);
                return ResponseEntity.status(HttpStatus.OK).
                        body(UserResponse.builder().response(userDTO).build());
            }
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, userRequest.getId()));
    }

    public ResponseEntity<UserResponse> deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            UserResponse response = UserResponse.builder().message(String.format(ServiceConstants.ENTITY_NOT_FOUND, id)).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
