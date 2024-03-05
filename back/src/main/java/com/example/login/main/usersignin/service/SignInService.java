package com.example.login.main.usersignin.service;

import com.example.login.main.usersignin.dto.SignInDto;
import com.example.login.main.usersignin.dto.SignInResultDto;
import com.example.login.main.usersignup.model.User;
import com.example.login.main.usersignup.repository.UserRepository;
import com.example.login.main.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public SignInResultDto login(SignInDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return new SignInResultDto(jwtUtil.generateToken(user));
    }
}
