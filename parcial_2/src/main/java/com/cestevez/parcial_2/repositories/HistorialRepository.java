package com.cestevez.parcial_2.repositories;

import com.cestevez.parcial_2.models.entities.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistorialRepository extends JpaRepository<Historial, UUID> {
}
