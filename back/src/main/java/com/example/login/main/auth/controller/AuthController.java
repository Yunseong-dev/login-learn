package com.example.login.main.auth.controller;

import com.example.login.main.auth.dto.DeleteUserDto;
import com.example.login.main.auth.dto.UserUpdateDto;
import com.example.login.main.user.model.User;
import com.example.login.main.auth.dto.AuthDto;
import com.example.login.main.auth.dto.AuthResultDto;
import com.example.login.main.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/change")
    public AuthResultDto UpdateUser(
            @RequestBody UserUpdateDto dto
    ){
        return authService.updateUser(dto);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> DeleteUser(
            @RequestBody DeleteUserDto dto
    ){
      return authService.deleteUser(dto);
    }
}
