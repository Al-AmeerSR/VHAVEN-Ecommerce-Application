package com.vhaven.vhavenapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
