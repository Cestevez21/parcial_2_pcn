package com.cestevez.parcial_2.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "historial")
public class Historial {
    @Id
    @Column(name = "historial_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID historial_id;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
