package com.cestevez.parcial_2.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterDTO {
    @NotEmpty(message = "username vacío")
    @Size(min = 8, max = 8)
    @Pattern(regexp = "^[a-zA-Z]{2}[a-zA-Z0-9]{6}$")
    private String username;

    @NotEmpty(message = "nombre vacío")
    private String nombre;

    @NotEmpty(message = "apellido vacío")
    private String apellido;

    @NotEmpty(message = "password vacío")
    @Size(min = 8, max = 32)
    private String password;

    @NotEmpty(message = "email vacío")
    @Email(message = "Formato de email incorrecto")
    private String email;

    @NotEmpty(message = "email vacío")
    @Email(message = "Formato de email incorrecto")
    private String dui;

    private Boolean active;
}