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
@Table(name = "asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private boolean presente;

    @ManyToOne
    @JoinColumn(name = "aspirante_id")
    private Aspirante aspirante;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    public Asistencia() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }
    public Aspirante getAspirante() { return aspirante; }
    public void setAspirante(Aspirante aspirante) { this.aspirante = aspirante; }
    public Clase getClase() { return clase; }
    public void setClase(Clase clase) { this.clase = clase; }
}
