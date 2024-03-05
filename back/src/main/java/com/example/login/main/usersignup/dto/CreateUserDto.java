package com.example.login.main.usersignup.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserDto {
    private String name;
    private String id;
    private String email;
    private String password;
    private LocalDateTime updatedAt;
}
