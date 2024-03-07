package com.example.login.main.auth.controller;

import com.example.login.main.auth.dto.UserUpdateDto;
import com.example.login.main.user.model.User;
import com.example.login.main.auth.dto.PasswordChackDto;
import com.example.login.main.auth.dto.AuthDto;
import com.example.login.main.auth.dto.AuthResultDto;
import com.example.login.main.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("")
    public AuthResultDto loginResultDto(
            @RequestBody AuthDto dto
    ) {
        return authService.login(dto);
    }

    @GetMapping("/userinfo")
    public User InfoUser(
            @AuthenticationPrincipal User user
    ){
        return user;
    }

    @PostMapping("/Change")
    public AuthResultDto UpdateUser(
            @RequestBody UserUpdateDto dto
    ){
        return authService.UpdateUser(dto);
    }
}
