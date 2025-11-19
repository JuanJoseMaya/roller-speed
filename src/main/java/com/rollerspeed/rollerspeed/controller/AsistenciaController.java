package com.rollerspeed.rollerspeed.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rollerspeed.rollerspeed.entity.Asistencia;
import com.rollerspeed.rollerspeed.entity.Aspirante;
import com.rollerspeed.rollerspeed.entity.Clase;
import com.rollerspeed.rollerspeed.repository.AsistenciaRepository;
import com.rollerspeed.rollerspeed.repository.AspiranteRepository;
import com.rollerspeed.rollerspeed.repository.ClaseRepository;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private ClaseRepository claseRepository;
    @Autowired
    private AspiranteRepository aspiranteRepository;

    // 1. PANTALLA DE SELECCIÓN
    @GetMapping("/nueva")
    public String iniciarTomarAsistencia(Model model) {
        model.addAttribute("titulo", "Tomar Asistencia");
        model.addAttribute("clases", claseRepository.findAll());
        return "asistencia-selector";
    }

    // 2. GUARDAR ASISTENCIA (Simplificado: Marca presente a un alumno)
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long claseId, @RequestParam Long aspiranteId) {
        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(LocalDate.now());
        asistencia.setPresente(true);
        
        // Buscamos las entidades completas
        Clase clase = claseRepository.findById(claseId).orElse(null);
        Aspirante aspirante = aspiranteRepository.findById(aspiranteId).orElse(null);

        if(clase != null && aspirante != null) {
            asistencia.setClase(clase);
            asistencia.setAspirante(aspirante);
            asistenciaRepository.save(asistencia);
        }
        
        return "redirect:/admin/dashboard";
    }
}
