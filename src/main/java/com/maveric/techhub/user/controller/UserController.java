package com.maveric.techhub.user.controller;
import com.maveric.techhub.user.model.UserRequest;
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
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable(name = "id") String id) {
        return userService.getUser(id);
    }

    @GetMapping("/getUserByEmail/{emailId}")
    ResponseEntity<UserResponse> getUserByEmailId(@PathVariable(name = "emailId") String emailId) {
        return userService.getUserByEmailId(emailId);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable(name = "id") String id,
                                            @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<UserResponse> deleteUser(@PathVariable(name = "id") String id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    ResponseEntity<UserResponse> getUsers() {
        return userService.getUsers();
    }

}