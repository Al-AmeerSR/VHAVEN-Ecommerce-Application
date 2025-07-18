package com.vhaven.vhavenapp.service;

import com.vhaven.vhavenapp.dto.LoginRequestDTO;
import com.vhaven.vhavenapp.dto.RegisterRequestDTO;
import com.vhaven.vhavenapp.entity.ROLE;
import com.vhaven.vhavenapp.entity.User;
import com.vhaven.vhavenapp.exception.UserNotFoundException;
import com.vhaven.vhavenapp.exception.UserWithEmailExistsException;
import com.vhaven.vhavenapp.mapper.UserMapper;
import com.vhaven.vhavenapp.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository
            , BCryptPasswordEncoder bCryptPasswordEncoder
            , AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public void register(RegisterRequestDTO dto, ROLE role) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new UserWithEmailExistsException(role + " with this email exists");
        }
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        User user = UserMapper.UserRegisterRequestDtoToEntityMapper(dto);
        user.setRole(role);
        userRepository.save(user);
    }

    public String authenticate(LoginRequestDTO user) {

        User authenticatedUser = userRepository.findByEmail(user.getEmail());
        if (authenticatedUser == null) {
            throw new UserNotFoundException(user.getEmail() + " with this email doesn't exists");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );


        return getAuthenticationMessage(authenticatedUser);
    }

    private String getAuthenticationMessage(User authenticatedUser) {
        return switch (authenticatedUser.getRole()) {
            case ADMIN -> "User Authenticated with role: ADMIN";
            case STAFF -> "User Authenticated with role: STAFF";
            case USER -> "User Authenticated with role: USER";
            default -> "User does not have a valid role";
        };


    }
}
