package com.maveric.techhub.user.controller;
import java.util.List;
import com.maveric.techhub.user.entity.User;
import com.maveric.techhub.user.model.CreateUserRequest;
import com.maveric.techhub.user.model.UpdateUserRequest;
import com.maveric.techhub.user.model.UserResponse;
import com.maveric.techhub.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * UserController is the class to manage for user CRUD operations
 * @author      Kannabiran Shanmugam
 * @version     %I%, %G%
 * @since       1.0
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest userRequest) {
        return userService.updateUser(userRequest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<UserResponse> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

}