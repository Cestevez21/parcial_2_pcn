package com.cestevez.parcial_2.services;

import com.cestevez.parcial_2.models.dtos.UserRegisterDTO;
import com.cestevez.parcial_2.models.entities.Token;
import com.cestevez.parcial_2.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void register(UserRegisterDTO info);
    User findOneByIdentifier(String identifier);

    User findOneByUsernameOrEmail(String username, String email);

    List<User> findAll();
    User findOneByEmail(String email);
    User findOneByUsername(String username);
    boolean checkPassword(String password, String password1);
    void changePassword(String identifier, String password) throws Exception;

    void changeActive(UUID identifier, Boolean active);

    //Token management
    Token registerToken(User user);
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user);


    User findUserAuthenticated();
}
