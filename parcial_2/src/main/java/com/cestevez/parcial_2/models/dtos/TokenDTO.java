package com.cestevez.parcial_2.models.dtos;


import com.cestevez.parcial_2.models.entities.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String token;

    public TokenDTO(Token token) {
        this.token = token.getContent();
    }

}