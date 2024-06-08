package com.cestevez.parcial_2.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthLoginDTO {
    @NotEmpty
    private String identifier;

    @NotEmpty
    private String password;
}