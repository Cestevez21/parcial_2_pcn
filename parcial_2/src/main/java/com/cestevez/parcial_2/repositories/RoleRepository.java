package com.cestevez.parcial_2.repositories;

import com.cestevez.parcial_2.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
