package com.vhaven.vhavenapp.controller;

import com.vhaven.vhavenapp.dto.LoginRequestDTO;
import com.vhaven.vhavenapp.dto.RegisterRequestDTO;
import com.vhaven.vhavenapp.entity.ROLE;
import com.vhaven.vhavenapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

        userService.register(registerRequestDTO, ROLE.USER);
        return ResponseEntity.ok("User registered successfully");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/staff-register")
    public ResponseEntity<String> registerStaff(@RequestBody RegisterRequestDTO registerRequestDTO) {

        userService.register(registerRequestDTO, ROLE.STAFF);
        return ResponseEntity.ok("Staff registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok( userService.authenticate(loginRequestDTO));
    }

    @PostMapping("/staff-login")
    public ResponseEntity<String> loginStaff(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok( userService.authenticate(loginRequestDTO));
    }

    @PostMapping("/admin-login")
    public ResponseEntity<String> loginAdmin(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok( userService.authenticate(loginRequestDTO));
    }
}
