package com.rollerspeed.rollerspeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rollerspeed.rollerspeed.entity.Pago;
import com.rollerspeed.rollerspeed.repository.AsistenciaRepository;
import com.rollerspeed.rollerspeed.repository.AspiranteRepository;
import com.rollerspeed.rollerspeed.repository.ClaseRepository;
import com.rollerspeed.rollerspeed.repository.InstructorRepository;
import com.rollerspeed.rollerspeed.repository.PagoRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Inyectamos TODOS los repositorios para tener acceso a toda la información
    @Autowired private AspiranteRepository aspiranteRepository;
    @Autowired private InstructorRepository instructorRepository;
    @Autowired private ClaseRepository claseRepository;
    @Autowired private PagoRepository pagoRepository;
    @Autowired private AsistenciaRepository asistenciaRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("titulo", "Panel de Control");
        
        // 1. Estadísticas de Personas (Cuenta cuántos hay)
        model.addAttribute("totalAlumnos", aspiranteRepository.count());
        model.addAttribute("totalInstructores", instructorRepository.count());
        
        // 2. Estadísticas Académicas (Cuenta registros)
        model.addAttribute("totalClases", claseRepository.count());
        model.addAttribute("totalAsistencias", asistenciaRepository.count());
        
        // 3. Estadísticas Financieras (Suma todo el dinero recaudado)
        // Usamos un stream de Java para sumar la columna 'monto' de todos los pagos
        double totalIngresos = pagoRepository.findAll()
                .stream()
                .mapToDouble(Pago::getMonto)
                .sum();
                
        model.addAttribute("totalIngresos", totalIngresos);
        
        return "admin-dashboard"; // Carga la vista admin-dashboard.html
    }
}
