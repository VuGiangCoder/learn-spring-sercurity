package com.spring.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AttemptsService {
    private final AttemptsRepository attemptsRepository;

    public List<Attempts> getAttemptsByEmail(String username){
        return attemptsRepository.findByUsername(username);
    }
}