package com.assignment.ecommerce.controller;

import com.assignment.ecommerce.exception.UserNotFoundException;

import com.assignment.ecommerce.model.Users;
import com.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")

    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findUserById(@PathVariable Long id) {
        Users user = userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.deleteUser(id);
    }
}



//package com.assignment.ecommerce.controller;
//
//import com.assignment.ecommerce.exception.UserNotFoundException;
//import com.assignment.ecommerce.model.Role;
//import com.assignment.ecommerce.model.Users;
//import com.assignment.ecommerce.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<Users>> getAllUsers() {
//        List<Users> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Users> createUser(@RequestBody Users user) {
//        Users savedUser = userService.createUser(user);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR') or #id == principal.id")
//    public ResponseEntity<Users> findUserById(@PathVariable Long id) {
//        Users user = userService.findUserById(id);
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
//    public ResponseEntity<Users> updateUser(@RequestBody Users updatedUser, @PathVariable Long id) {
//        Users existingUser = userService.findUserById(id);
//        if (existingUser == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        // Only allow updating the user's own details or if the user is an admin
//        // You might want to implement more specific checks based on the fields being updated
//        if (hasRole("ADMIN") || (getCurrentUserId() != null && getCurrentUserId().equals(id))) {
//            updatedUser.setId(id); // Ensure the ID in the request matches the path variable
//            Users savedUser = userService.updateUser(updatedUser);
//            return new ResponseEntity<>(savedUser, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException {
//        return userService.deleteUser(id);
//    }
//
//    // Helper method to check if the current user has a specific role
//    private boolean hasRole(String role) {
//        // Implementation depends on your Spring Security setup
//        // This is a basic example and might need adjustments based on your authentication
//        // For a more robust solution, use SecurityContextHolder directly.
//        // Example using SecurityContextHolder:
//        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role))) {
//        //     return true;
//        // }
//        return false; // Replace with actual role checking logic
//    }
//
//    // Helper method to get the ID of the currently authenticated user
//    private Long getCurrentUserId() {
//        // Implementation depends on how you store user information in your authentication
//        // For example, if you have a custom UserDetails implementation:
//        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
//        //     return ((UserDetailsImpl) authentication.getPrincipal()).getId();
//        // }
//        return null; // Replace with actual ID retrieval logic
//    }
//}

