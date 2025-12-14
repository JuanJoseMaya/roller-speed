package com.rollerspeed.rollerspeed.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.Aspirante;
import com.rollerspeed.rollerspeed.repository.AspiranteRepository;
import com.rollerspeed.rollerspeed.service.IAspiranteService;

@Service // ¡Muy importante!
public class AspiranteServiceImpl implements IAspiranteService {

    @Autowired
    private AspiranteRepository aspiranteRepository;

    @Override
    public List<Aspirante> listarTodos() {
        return aspiranteRepository.findAll();
    }

    @Override
    public Optional<Aspirante> buscarPorId(Long id) {
        return aspiranteRepository.findById(id);
    }

    @Override
    public Aspirante guardar(Aspirante aspirante) {
        return aspiranteRepository.save(aspirante);
    }

    @Override
    public void eliminar(Long id) {
        aspiranteRepository.deleteById(id);
    }

    @Override
    public long contar() {
        return aspiranteRepository.count();
    }
}
