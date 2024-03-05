package com.example.login.main.usersignup.controller;


import com.example.login.main.usersignup.dto.CreateUserDto;
import com.example.login.main.usersignup.model.User;
import com.example.login.main.usersignup.service.SignUpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class SignUpController {
    private final SignUpService userService;

    @PostMapping("")
    public User createUser(
            @RequestBody CreateUserDto dto
    ){
        return userService.createUser(dto);
    }
}
