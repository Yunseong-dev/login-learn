package com.example.login.main.auth.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime updatedAt;
}
