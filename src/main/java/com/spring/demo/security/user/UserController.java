package com.spring.demo.security.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @RequestMapping("/")
    public Optional<User> getUserByEmail(String username){
        return userService.getUserByUsername(username);
    }
}
