package com.cestevez.parcial_2.repositories;

import com.cestevez.parcial_2.models.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CitaRepository extends JpaRepository<Cita, UUID> {
}
