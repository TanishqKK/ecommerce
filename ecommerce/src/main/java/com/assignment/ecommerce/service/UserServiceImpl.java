package com.assignment.ecommerce.service;

import com.assignment.ecommerce.model.Users;
import com.assignment.ecommerce.exception.UserNotFoundException;
import com.assignment.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Users createUser(Users user) {
        return repository.save(user);
    }

    @Override
    public Users findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public Users updateUser(Users user) {
        Users existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return repository.save(existingUser);
        }
        return null;
    }



    @Override
    public ResponseEntity<String> deleteUser(Long id) throws UserNotFoundException {
        Optional<Users> usersOptional = repository.findById(id);

        if (usersOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("User number " + id + " deleted");
        } else {
            // Check if user was previously deleted
            if (repository.existsById(id)) {
                throw new UserNotFoundException("User with id " + id + " already deleted");
            } else {
                throw new UserNotFoundException("User with id " + id + " not found or already deleted");
            }
        }
    }
}
