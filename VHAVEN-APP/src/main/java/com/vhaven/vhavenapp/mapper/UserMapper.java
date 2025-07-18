package com.vhaven.vhavenapp.mapper;

import com.vhaven.vhavenapp.dto.RegisterRequestDTO;
import com.vhaven.vhavenapp.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User UserRegisterRequestDtoToEntityMapper (RegisterRequestDTO registerRequestDTO) {

        User user = new User();
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setLastName(registerRequestDTO.getLastName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        return user;
    }


}
