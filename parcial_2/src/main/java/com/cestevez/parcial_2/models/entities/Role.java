package com.cestevez.parcial_2.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "roles_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roles_id;
    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<User> users;
}
