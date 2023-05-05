package com.spring.demo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptsRepository extends JpaRepository<Attempts,Integer> {
    List<Attempts> findByUsername(String username);
}
