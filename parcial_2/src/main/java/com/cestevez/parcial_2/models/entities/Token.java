package com.cestevez.parcial_2.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tokenId")
    private UUID tokenId;

    @Column(name = "content")
    public String content;

    @Column(name = "timestamp", updatable = false)
    public Date timestamp;

    @Column(name = "active")
    public Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    @JsonIgnore
    private User user;

    public Token(String content, User user) {
        this.content = content;
        this.user = user;
        this.timestamp = Date.from(Instant.now());
        this.active = true;
    }
}
