package com.vhaven.vhavenapp.controller;

import com.vhaven.vhavenapp.dto.UserLoginRequestDTO;
import com.vhaven.vhavenapp.entity.User;
import com.vhaven.vhavenapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {

        return ResponseEntity.ok( userService.loginUser(userLoginRequestDTO));
    }
}
