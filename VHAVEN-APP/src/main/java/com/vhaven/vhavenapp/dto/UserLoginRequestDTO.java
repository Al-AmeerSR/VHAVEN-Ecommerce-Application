package com.vhaven.vhavenapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequestDTO {

    private String email;
    private String password;
}
