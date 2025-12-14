package com.rollerspeed.rollerspeed.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; // Usamos el Servicio
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rollerspeed.rollerspeed.entity.Aspirante;
import com.rollerspeed.rollerspeed.service.IAspiranteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Gestión de Aspirantes", description = "Controlador principal para registro y gestión")
public class HomeController {

    @Autowired
    private IAspiranteService aspiranteService; // Inyección del Servicio

    // --- PÁGINAS PÚBLICAS ---

    @Operation(summary = "Inicio", description = "Muestra la página de bienvenida")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Inicio - Roller Speed");
        return "index";
    }

    @GetMapping("/mision") public String mision(Model model) { model.addAttribute("titulo", "Misión"); return "mision"; }
    @GetMapping("/vision") public String vision(Model model) { model.addAttribute("titulo", "Visión"); return "vision"; }
    @GetMapping("/valores") public String valores(Model model) { model.addAttribute("titulo", "Valores"); return "valores"; }
    @GetMapping("/servicios") public String servicios(Model model) { model.addAttribute("titulo", "Servicios"); return "servicios"; }
    @GetMapping("/eventos") public String eventos(Model model) { model.addAttribute("titulo", "Eventos"); return "eventos"; }
    @GetMapping("/login") public String login() { return "login"; }

    // --- GESTIÓN DE ALUMNOS (CRUD) ---

    @Operation(summary = "Formulario Registro", description = "Muestra el formulario vacío o precargado")
    @GetMapping("/registro")
    public String mostrarRegistro(@RequestParam(name = "plan", required = false) String plan, Model model) {
        model.addAttribute("titulo", "Inscripción");
        Aspirante aspirante = new Aspirante();
        if (plan != null) aspirante.setPlan(plan);
        model.addAttribute("aspirante", aspirante);
        model.addAttribute("planSeleccionado", plan);
        return "registro";
    }

    @Operation(summary = "Editar Alumno", description = "Carga datos de un alumno existente")
    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Alumno");
        Optional<Aspirante> aspirante = aspiranteService.buscarPorId(id);

        if (aspirante.isPresent()) {
            model.addAttribute("aspirante", aspirante.get());
            return "registro"; 
        } else {
            return "redirect:/ver-inscritos";
        }
    }

    @Operation(summary = "Guardar Alumno", description = "Guarda o actualiza un aspirante en la BD")
    @PostMapping("/guardar-aspirante")
    public String guardarAspirante(@ModelAttribute Aspirante aspirante) {
        aspiranteService.guardar(aspirante);
        return "redirect:/exito";
    }

    @GetMapping("/exito")
    public String mostrarExito(Model model) {
        model.addAttribute("titulo", "¡Registro Exitoso!");
        return "exito";
    }

    @Operation(summary = "Listar Alumnos", description = "Muestra la tabla de todos los inscritos")
    @GetMapping("/ver-inscritos")
    public String verInscritos(Model model) {
        model.addAttribute("titulo", "Listado de Alumnos");
        model.addAttribute("aspirantes", aspiranteService.listarTodos());
        return "ver-inscritos";
    }

    @Operation(summary = "Eliminar Alumno", description = "Borra un aspirante por su ID")
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        aspiranteService.eliminar(id);
        return "redirect:/ver-inscritos";
    }
}
 