package com.spring.demo.security.auth;

import com.spring.demo.security.config.JwtService;
import com.spring.demo.security.user.User;
import com.spring.demo.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .username(request.getUsername())
                .age(request.getAge())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var saveUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()

                ));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
