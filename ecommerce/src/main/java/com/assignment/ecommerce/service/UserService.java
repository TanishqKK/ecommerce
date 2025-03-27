package com.assignment.ecommerce.service;

import com.assignment.ecommerce.exception.UserNotFoundException;
import com.assignment.ecommerce.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();
    Users createUser(Users user);
    Users findUserById(Long id);
    Users updateUser(Users user);
    ResponseEntity<String> deleteUser(Long id) throws UserNotFoundException;
}