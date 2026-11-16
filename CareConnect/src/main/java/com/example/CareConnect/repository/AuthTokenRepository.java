package com.example.CareConnect.repository;
import com.example.CareConnect.entity.AuthToken;
import com.example.CareConnect.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken,Long> {
     Optional<AuthToken> findByToken(String token);

    Optional<AuthToken> findByUser(User user);

    void deleteByToken(String token);
    
}
