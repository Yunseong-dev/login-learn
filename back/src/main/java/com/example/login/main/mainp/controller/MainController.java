package com.example.login.main.mainp.controller;

import com.example.login.main.usersignup.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("")
@AllArgsConstructor
public class MainController {

    @GetMapping("/userinfo")
    public User InfoUser(
            @AuthenticationPrincipal User user
    ){
        return user;
    }
}
