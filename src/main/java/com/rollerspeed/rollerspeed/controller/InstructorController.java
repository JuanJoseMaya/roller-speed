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

import com.rollerspeed.rollerspeed.entity.Instructor;
import com.rollerspeed.rollerspeed.repository.InstructorRepository;

@Controller
@RequestMapping("/instructores") // Todas las rutas empezarán por /instructores
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    // 1. VER LISTA DE INSTRUCTORES
    @GetMapping("/lista")
    public String verLista(Model model) {
        model.addAttribute("titulo", "Equipo de Instructores");
        model.addAttribute("instructores", instructorRepository.findAll());
        return "ver-instructores";
    }

    // 2. FORMULARIO DE REGISTRO (NUEVO)
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Nuevo Instructor");
        model.addAttribute("instructor", new Instructor());
        return "registro-instructor";
    }

    // 3. FORMULARIO DE EDICIÓN
    @GetMapping("/editar/{id}")
    public String editarInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Instructor");
        Optional<Instructor> instructor = instructorRepository.findById(id);
        
        if (instructor.isPresent()) {
            model.addAttribute("instructor", instructor.get());
            return "registro-instructor";
        } else {
            return "redirect:/instructores/lista";
        }
    }

    // 4. GUARDAR (SIRVE PARA NUEVO Y EDITAR)
    @PostMapping("/guardar")
    public String guardarInstructor(@ModelAttribute Instructor instructor) {
        instructorRepository.save(instructor);
        return "redirect:/instructores/lista";
    }

    // 5. ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarInstructor(@PathVariable Long id) {
        instructorRepository.deleteById(id);
        return "redirect:/instructores/lista";
    }
}
