package com.example.login.main.usersignin.controller;

import com.example.login.main.usersignin.dto.SignInDto;
import com.example.login.main.usersignin.dto.SignInResultDto;
import com.example.login.main.usersignin.service.SignInService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/signin")
@AllArgsConstructor
public class SignInController {
    private final SignInService authService;

    @PostMapping("")
    public SignInResultDto loginResultDto(
            @RequestBody SignInDto dto
    ) {
        return authService.login(dto);
    }
}
