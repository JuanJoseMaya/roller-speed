package com.rollerspeed.rollerspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.Clase;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
}
