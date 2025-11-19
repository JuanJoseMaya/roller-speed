package com.rollerspeed.rollerspeed.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rollerspeed.rollerspeed.entity.Pago;
import com.rollerspeed.rollerspeed.repository.AspiranteRepository;
import com.rollerspeed.rollerspeed.repository.PagoRepository;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private AspiranteRepository aspiranteRepository; // Para cargar la lista de alumnos

    // 1. VER HISTORIAL DE PAGOS
    @GetMapping("/lista")
    public String verPagos(Model model) {
        model.addAttribute("titulo", "Finanzas");
        model.addAttribute("pagos", pagoRepository.findAll());
        
        // Calculamos el total recaudado
        double totalIngresos = pagoRepository.findAll().stream().mapToDouble(Pago::getMonto).sum();
        model.addAttribute("totalIngresos", totalIngresos);
        
        return "ver-pagos";
    }

    // 2. REGISTRAR NUEVO PAGO
    @GetMapping("/nuevo")
    public String nuevoPago(Model model) {
        model.addAttribute("titulo", "Registrar Pago");
        
        Pago pago = new Pago();
        pago.setFechaPago(LocalDate.now()); // Fecha de hoy por defecto
        
        model.addAttribute("pago", pago);
        model.addAttribute("aspirantes", aspiranteRepository.findAll()); // Lista para el select
        
        return "registro-pago";
    }

    // 3. GUARDAR
    @PostMapping("/guardar")
    public String guardarPago(@ModelAttribute Pago pago) {
        pagoRepository.save(pago);
        return "redirect:/pagos/lista";
    }
    
    // 4. ELIMINAR (Ej: Pago registrado por error)
    @GetMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
        return "redirect:/pagos/lista";
    }
}
