package com.acleda.student.jwt;

import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/register")
    public ResponseEntity<?> createUserHandler(@Valid @RequestBody UserRequest userRequest) throws Exception {
        // log.info("User registration request received: {}",
        // JsonLogger.toJson(userRequest));

        UserModel existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser != null) {
            throw new BadRequestException("Email is already associated with another account.");
        }

        UserModel newUser = new UserModel();
        newUser.setName(userRequest.getFullName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setRole(userRequest.getRole());
        // newUser.setCreatedAt(LocalDateTime.now());
        // newUser.setCreatedBy(Constant.SYSTEM);
        UserModel savedUser = userRepository.save(newUser);

        // Authenticate user after registration
        Authentication authentication = new UsernamePasswordAuthenticationToken(userRequest.getEmail(),
                userRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        // AuthResponse authResponse = new AuthResponse();
        // authResponse.setJwtToken(jwt);
        // authResponse.setRole(savedUser.getRole());

        // log.info("User registration successful for email: {}", savedUser.getEmail());
        // log.debug("Registration response: {}", JsonLogger.toJson(authResponse));

        return new ResponseEntity<>(
                "User registration successful.",
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        // log.info("User login request received: {}", JsonLogger.toJson(loginRequest));

        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);

        // AuthResponse authResponse = new AuthResponse();
        // authResponse.setJwtToken(jwt);
        // authResponse.setRole(role);

        // log.info("User login successful for email: {}", loginRequest.getEmail());
        // log.debug("Login response: {}", JsonLogger.toJson(authResponse));

        return new ResponseEntity<>(
                // ApiResponseUtil.successResponse("User login successful.", authResponse),
                HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        if (userDetails == null) {
            // throw new BadRequestErrorException("Invalid username.");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            // throw new BadRequestErrorException("Invalid password.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
