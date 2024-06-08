package com.cestevez.parcial_2.controllers;


import com.cestevez.parcial_2.models.dtos.AuthLoginDTO;
import com.cestevez.parcial_2.models.dtos.TokenDTO;
import com.cestevez.parcial_2.models.dtos.UserRegisterDTO;
import com.cestevez.parcial_2.models.entities.Token;
import com.cestevez.parcial_2.models.entities.User;
import com.cestevez.parcial_2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/API/v1/OpenSecurity/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/singup")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRegisterDTO Info, @RequestHeader("identifier") String identifier) {
        User userFound = userService.findOneByIdentifier(identifier);

        User userEmail = userService.findOneByEmail(Info.getEmail());
        User userUsername = userService.findOneByUsername(Info.getUsername());

        if (userEmail != null || userUsername != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Response", "Usuario/email ya existe",
                    "status", HttpStatus.CONFLICT));
        }
        try {
            userService.register(Info);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Response", "Usuario registrado",
                    "status", HttpStatus.CREATED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Response", "Error al registrar el usuario",
                    "status", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthLoginDTO Info) {
        User userFound = userService.findOneByIdentifier(Info.getIdentifier());
        if (userFound == null) {
            System.out.println("No fue encontrado en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "Response", "No fue encontrado en la base de datos",
                    "status", HttpStatus.NOT_FOUND));
        }


        if (!userService.checkPassword(Info.getPassword(), userFound.getPassword())) {
            System.out.println("Datos incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "Response", "Datos incorrectos",
                    "status", HttpStatus.UNAUTHORIZED));
        }
        System.out.println("Usuario Logueado");
        try {
            Token token = userService.registerToken(userFound);
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
