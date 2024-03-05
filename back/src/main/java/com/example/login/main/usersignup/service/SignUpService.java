package com.example.login.main.usersignup.service;

import com.example.login.main.usersignup.dto.CreateUserDto;
import com.example.login.main.usersignup.model.User;
import com.example.login.main.usersignup.repository.UserRepository;
import com.example.login.main.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SignUpService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto dto) {
        LocalDateTime now = LocalDateTime.now();
        if (userRepository.existsById(dto.getId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = new User(
                dto.getName(),
                dto.getId(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                now,
                now
        );

        return userRepository.save(user);
    }
}
