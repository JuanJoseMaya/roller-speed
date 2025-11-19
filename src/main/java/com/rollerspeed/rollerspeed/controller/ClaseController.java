package com.rollerspeed.rollerspeed.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rollerspeed.rollerspeed.entity.Clase;
import com.rollerspeed.rollerspeed.repository.ClaseRepository;
import com.rollerspeed.rollerspeed.repository.InstructorRepository;

@Controller
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private InstructorRepository instructorRepository; // Necesario para cargar la lista de profes

    // 1. VER HORARIOS
    @GetMapping("/lista")
    public String verLista(Model model) {
        model.addAttribute("titulo", "Programación de Clases");
        model.addAttribute("clases", claseRepository.findAll());
        return "ver-clases";
    }

    // 2. NUEVA CLASE
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Programar Clase");
        model.addAttribute("clase", new Clase());
        // Enviamos la lista de instructores para el select
        model.addAttribute("instructores", instructorRepository.findAll());
        return "registro-clase";
    }

    // 3. EDITAR CLASE
    @GetMapping("/editar/{id}")
    public String editarClase(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Clase");
        Optional<Clase> clase = claseRepository.findById(id);
        
        if (clase.isPresent()) {
            model.addAttribute("clase", clase.get());
            model.addAttribute("instructores", instructorRepository.findAll()); // También aquí
            return "registro-clase";
        } else {
            return "redirect:/clases/lista";
        }
    }

    // 4. GUARDAR
    @PostMapping("/guardar")
    public String guardarClase(@ModelAttribute Clase clase) {
        claseRepository.save(clase);
        return "redirect:/clases/lista";
    }

    // 5. ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarClase(@PathVariable Long id) {
        claseRepository.deleteById(id);
        return "redirect:/clases/lista";
    }
}
