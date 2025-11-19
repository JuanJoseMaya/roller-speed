package com.rollerspeed.rollerspeed.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rollerspeed.rollerspeed.entity.Aspirante;
import com.rollerspeed.rollerspeed.repository.AspiranteRepository;

@Controller
public class HomeController {

    @Autowired
    private AspiranteRepository aspiranteRepository;

    // ==========================================
    // 1. PÁGINAS PÚBLICAS (ESTÁTICAS)
    // ==========================================

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Inicio - Roller Speed");
        return "index";
    }

    @GetMapping("/mision")
    public String mision(Model model) {
        model.addAttribute("titulo", "Nuestra Misión");
        return "mision";
    }

    @GetMapping("/vision")
    public String vision(Model model) {
        model.addAttribute("titulo", "Nuestra Visión");
        return "vision";
    }

    @GetMapping("/valores")
    public String valores(Model model) {
        model.addAttribute("titulo", "Valores Corporativos");
        return "valores";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("titulo", "Servicios de Patinaje");
        return "servicios";
    }

    @GetMapping("/eventos")
    public String eventos(Model model) {
        model.addAttribute("titulo", "Próximos Eventos");
        return "eventos";
    }

    // ==========================================
    // 2. SEGURIDAD (LOGIN) - ¡NUEVO!
    // ==========================================

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna la vista login.html
    }

    // ==========================================
    // 3. GESTIÓN DE ALUMNOS (CRUD COMPLETO)
    // ==========================================

    // A. CREAR
    @GetMapping("/registro")
    public String mostrarRegistro(@RequestParam(name = "plan", required = false) String plan, Model model) {
        model.addAttribute("titulo", "Inscripción");
        Aspirante aspirante = new Aspirante();
        if (plan != null) {
            aspirante.setPlan(plan);
        }
        model.addAttribute("aspirante", aspirante);
        model.addAttribute("planSeleccionado", plan);
        return "registro";
    }

    // B. EDITAR
    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Alumno");
        Optional<Aspirante> aspiranteEncontrado = aspiranteRepository.findById(id);
        
        if (aspiranteEncontrado.isPresent()) {
            model.addAttribute("aspirante", aspiranteEncontrado.get());
            return "registro"; 
        } else {
            return "redirect:/ver-inscritos";
        }
    }

    // C. GUARDAR
    @PostMapping("/guardar-aspirante")
    public String guardarAspirante(@ModelAttribute Aspirante aspirante) {
        aspiranteRepository.save(aspirante);
        return "redirect:/exito";
    }

    // D. ÉXITO
    @GetMapping("/exito")
    public String mostrarExito(Model model) {
        model.addAttribute("titulo", "¡Registro Exitoso!");
        return "exito";
    }

    // E. LEER (Protegido)
    @GetMapping("/ver-inscritos")
    public String verInscritos(Model model) {
        model.addAttribute("titulo", "Listado de Alumnos");
        model.addAttribute("aspirantes", aspiranteRepository.findAll());
        return "ver-inscritos";
    }

    // F. ELIMINAR (Protegido)
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        aspiranteRepository.deleteById(id);
        return "redirect:/ver-inscritos";
    }
}
