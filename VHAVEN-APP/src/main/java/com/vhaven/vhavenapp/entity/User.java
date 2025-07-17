package com.vhaven.vhavenapp.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name ="users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role;
}
