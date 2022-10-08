package com.maveric.techhub.user.repository;

import com.maveric.techhub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String Id);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmployeeId(String employeeId);

}