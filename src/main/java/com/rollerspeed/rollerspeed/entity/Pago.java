package com.rollerspeed.rollerspeed.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPago;
    private String mesPagado; // Ej: "Noviembre 2025"
    private Double monto;
    private String metodo;    // Efectivo, Transferencia
    private String estado;    // Aprobado, Pendiente

    // RELACIÓN: Un pago pertenece a un alumno
    @ManyToOne
    @JoinColumn(name = "aspirante_id")
    private Aspirante aspirante;

    public Pago() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
    public String getMesPagado() { return mesPagado; }
    public void setMesPagado(String mesPagado) { this.mesPagado = mesPagado; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Aspirante getAspirante() { return aspirante; }
    public void setAspirante(Aspirante aspirante) { this.aspirante = aspirante; }
}
