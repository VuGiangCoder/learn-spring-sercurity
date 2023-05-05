package com.spring.demo.security.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
