package com.cestevez.parcial_2.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @Column(name = "cita_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cita_id;
}
