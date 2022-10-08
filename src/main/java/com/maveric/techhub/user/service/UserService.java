package com.maveric.techhub.user.service;

import com.maveric.techhub.user.repository.UserRepository;
import com.maveric.techhub.user.entity.User;
import com.maveric.techhub.user.exception.EntityNotFoundException;
import com.maveric.techhub.user.mapper.UserMapper;
import com.maveric.techhub.user.model.UserDTO;
import com.maveric.techhub.user.model.UserRequest;
import com.maveric.techhub.user.model.UserResponse;
import com.maveric.techhub.user.util.ServiceConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        String employeeId = userRequest.getEmployeeId();
        String emailId = userRequest.getEmail();
        validateIfEmployeeIdExists(employeeId);
        validateIfEmailIdExists(emailId);
        User user = userMapper.toEntity(userRequest);
        user.setId(ServiceConstants._UUID());
        userRepository.save(user);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(UserResponse.builder().response(userDTO).build());
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

    public ResponseEntity<UserResponse> getUserByEmailId(String emailId) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(emailId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = userMapper.toDTO(user);
            return ResponseEntity.status(HttpStatus.OK).
                    body(UserResponse.builder().response(userDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, emailId));
    }

    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        String employeeId = userRequest.getEmployeeId();
        String emailId = userRequest.getEmail();
        Optional<User> optionalUserDB = userRepository.findById(id);
        if (optionalUserDB.isPresent()) {
            User userDB = optionalUserDB.get();
            if (!userDB.getEmployeeId().equalsIgnoreCase(employeeId)) {
                validateIfEmployeeIdExists(employeeId);
            }
            if (!userDB.getEmail().equalsIgnoreCase(emailId)) {
                validateIfEmailIdExists(emailId);
            }
            User user = userMapper.toEntity(userRequest);
            user.setId(userDB.getId());
            userRepository.save(user);
            UserDTO userDTO = userMapper.toDTO(user);
            return ResponseEntity.status(HttpStatus.OK).
                    body(UserResponse.builder().response(userDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public ResponseEntity<UserResponse> deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            UserResponse response = UserResponse.builder().
                    message(String.format(ServiceConstants.ENTITY_DELETED, id)).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public ResponseEntity<UserResponse> getUsers() {
        UserResponse response = UserResponse.builder().build();
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
//            List<UserDTO> userDTOs  = users.stream().map(user -> userMapper.toDTO(user)).collect(Collectors.toList());
            List<UserDTO> userDTOs = userMapper.toDTO(users);
            response.setResponse(userDTOs);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateIfEmployeeIdExists(String employeeId) {
        Optional<User> optionalUser = userRepository.findByEmployeeId(employeeId);
        if (optionalUser.isPresent()) {
            throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_ALREADY_EXISTS, employeeId));
        }
    }

    private void validateIfEmailIdExists(String email) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isPresent()) {
            throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_ALREADY_EXISTS, email));
        }
    }
}
