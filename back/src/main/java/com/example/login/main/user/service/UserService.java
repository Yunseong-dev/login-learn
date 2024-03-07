package com.example.login.main.user.service;

import com.example.login.main.user.dto.CreateUserDto;
import com.example.login.main.user.model.User;
import com.example.login.main.user.repository.UserRepository;
import com.example.login.main.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto dto) {
        LocalDateTime now = LocalDateTime.now();
        if (userRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."
            );
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
