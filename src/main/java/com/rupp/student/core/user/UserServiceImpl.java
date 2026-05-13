package com.rupp.student.core.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rupp.student.core.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public boolean hasUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String login(UserEntity user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return jwtUtil.generateToken(user.getUsername());
    }
}