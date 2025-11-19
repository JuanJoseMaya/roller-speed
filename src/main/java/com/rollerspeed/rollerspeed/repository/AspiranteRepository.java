package com.rollerspeed.rollerspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.Aspirante;

@Repository
public interface AspiranteRepository extends JpaRepository<Aspirante, Long> {
    // ¡Listo! Spring crea el código para guardar datos automáticamente aquí.
}
