package com.example.login.main.auth.service;

import com.example.login.main.auth.dto.AuthDto;
import com.example.login.main.auth.dto.AuthResultDto;
import com.example.login.main.auth.dto.DeleteUserDto;
import com.example.login.main.auth.dto.UserUpdateDto;
import com.example.login.main.user.model.User;
import com.example.login.main.user.repository.UserRepository;
import com.example.login.main.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResultDto login(AuthDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");

        }

        return new AuthResultDto(jwtUtil.generateToken(user));
    }

    public AuthResultDto updateUser(UserUpdateDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);
        LocalDateTime now = LocalDateTime.now();

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        if (!passwordEncoder.matches(dto.getRepassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 틀렸습니다.");
        }

        else{
            if(dto.getPassword().isEmpty()){
                user.setName(dto.getName());
                user.setEmail(dto.getEmail());
                user.setUpdatedAt(now);
            }
            else{
                user.setName(dto.getName());
                user.setEmail(dto.getEmail());
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
                user.setUpdatedAt(now);
            }
        }
        userRepository.save(user);

        return new AuthResultDto(jwtUtil.generateToken(user));
    }

    public ResponseEntity<?> deleteUser(DeleteUserDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        if (!passwordEncoder.matches(dto.getRepassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 틀렸습니다.");
        }

        else {
            userRepository.delete(user);
        }

        return ResponseEntity.ok("회원 삭제가 성공적으로 이루어졌습니다.");
    }
}
