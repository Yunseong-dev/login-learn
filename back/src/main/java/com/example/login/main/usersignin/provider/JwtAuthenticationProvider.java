package com.example.login.main.usersignin.provider;


import com.example.login.main.usersignin.authentication.JwtAuthentication;
import com.example.login.main.usersignup.model.User;
import com.example.login.main.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String credentials = (String) authentication.getCredentials();
        String userId = jwtUtil.getSubject(credentials);
        User user = (User) userDetailsService.loadUserByUsername(userId);

        return new JwtAuthentication(credentials, user, true);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == JwtAuthentication.class;
    }

}
