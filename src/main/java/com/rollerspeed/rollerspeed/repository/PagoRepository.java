package com.rollerspeed.rollerspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}
