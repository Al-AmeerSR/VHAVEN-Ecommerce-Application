package com.vhaven.vhavenapp.service;

import com.vhaven.vhavenapp.dto.UserLoginRequestDTO;
import com.vhaven.vhavenapp.entity.User;
import com.vhaven.vhavenapp.exception.UserWithEmailExistsException;
import com.vhaven.vhavenapp.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager ;

    public UserService(UserRepository userRepository
    , BCryptPasswordEncoder bCryptPasswordEncoder
    , AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {

            throw new UserWithEmailExistsException("User with this email exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    public String loginUser(UserLoginRequestDTO user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        return "Authenticated";
    }


}
