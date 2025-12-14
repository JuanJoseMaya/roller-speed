package com.rollerspeed.rollerspeed.service;

import java.util.List;
import java.util.Optional;

import com.rollerspeed.rollerspeed.entity.Aspirante;

public interface IAspiranteService {
    // Contrato de operaciones
    List<Aspirante> listarTodos();
    Optional<Aspirante> buscarPorId(Long id);
    Aspirante guardar(Aspirante aspirante);
    void eliminar(Long id);
    long contar();
}
