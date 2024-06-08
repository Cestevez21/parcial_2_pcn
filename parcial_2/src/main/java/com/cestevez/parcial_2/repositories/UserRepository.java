package com.cestevez.parcial_2.repositories;

import com.cestevez.parcial_2.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository
        extends JpaRepository<User, UUID> {
    User findOneByEmail(String email);

    User findOneByUsername(String username);

    User findOneByUsernameOrEmail(String username, String email);

}