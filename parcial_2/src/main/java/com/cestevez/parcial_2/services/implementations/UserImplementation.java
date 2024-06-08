package com.cestevez.parcial_2.services.implementations;

import com.cestevez.parcial_2.models.dtos.UserRegisterDTO;
import com.cestevez.parcial_2.models.entities.Token;
import com.cestevez.parcial_2.models.entities.User;
import com.cestevez.parcial_2.repositories.TokenRepository;
import com.cestevez.parcial_2.repositories.UserRepository;
import com.cestevez.parcial_2.services.UserService;
import com.cestevez.parcial_2.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserImplementation implements UserService {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void register(UserRegisterDTO info) {
        User user =  new User(
                info.getNombre(),
                info.getApellido(),
                info.getUsername(),
                info.getEmail(),
                passwordEncoder.encode(info.getPassword()),
                info.getActive()
        );
        userRepository.save(user);
    }
    @Override
    public User findOneByIdentifier(String identifier) {
        return userRepository.findOneByUsernameOrEmail(identifier,identifier);
    }

    @Override
    public boolean checkPassword(String toCompare, String current) {
        return passwordEncoder.matches(toCompare, current);
    }

    @Override
    public User findOneByUsernameOrEmail(String username, String email) {
        return userRepository.findOneByUsernameOrEmail(username, email);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public void changeActive(UUID id, Boolean info) {
        // TODO Auto-generated method stub
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(info);
            userRepository.save(user);
        }
    }


    @Override
    public void changePassword(String identifier, String newPassword) throws Exception {
        User user = findOneByIdentifier(identifier);
        if (user != null) {
            // Password exists
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(Exception::new);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });

    }

    @Override
    public User findUserAuthenticated() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findOneByUsernameOrEmail(username, username);
    }
}
