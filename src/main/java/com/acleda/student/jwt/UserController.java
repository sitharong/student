package com.acleda.student.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * REST controller for user authentication and registration.
 * Handles user registration, login, and logout endpoints.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * 
     * @param user the user data to register (validated)
     * @return a message indicating success or if the username already exists
     */
    @PostMapping("/register")
    public String register(@Valid @RequestBody UserModel user) {
        if (userService.hasUsername(user.getUsername())) {
            return "Username already exists";
        }
        userService.saveUser(user);
        return "User registered";
    }

    /**
     * Authenticates a user and returns a JWT token if successful.
     * 
     * @param user the user credentials (validated)
     * @return the JWT token or an error message if authentication fails
     */
    @PostMapping("/login")
    public String login(@Valid @RequestBody UserModel user) {
        try {
            return userService.login(user);
        } catch (AuthenticationException e) {
            return "Invalid credentials";
        }
    }

    /**
     * Handles user logout.
     * For JWT, logout is handled on the client side by deleting the token.
     * 
     * @return a message indicating logout instructions
     */
    @PostMapping("/logout")
    public String logout() {
        // For JWT, logout is handled on the client side by deleting the token.
        return "Logged out. Please remove the token on the client side.";
    }

}