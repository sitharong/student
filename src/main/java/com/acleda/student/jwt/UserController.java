package com.acleda.student.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody UserModel user) {
        if (userService.hasUsername(user.getUsername())) {
            return "Username already exists";
        }
        userService.saveUser(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserModel user) {
        try {
            return userService.login(user);
        } catch (AuthenticationException e) {
            return "Invalid credentials";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        // For JWT, logout is handled on the client side by deleting the token.
        return "Logged out. Please remove the token on the client side.";
    }

}