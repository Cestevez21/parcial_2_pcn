package com.cestevez.parcial_2.repositories;

import com.cestevez.parcial_2.models.entities.Token;
import com.cestevez.parcial_2.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository
        extends JpaRepository<Token, UUID> {

    List<Token> findByUserAndActive(User user, Boolean active);
}
