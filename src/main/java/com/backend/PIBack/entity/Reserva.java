package com.backend.PIBack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "RESERVAS")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate fechaReserva = LocalDate.now();

    //@FutureOrPresent(message = "La fecha de reserva debe ser presente o futura")
    private LocalDate fechaInicio;

    //@FutureOrPresent(message = "La fecha de reserva debe ser presente o futura")
    private LocalDate fechaFin;

    @ManyToMany
    @JoinTable(name = "reservas_productos", joinColumns = @JoinColumn(name = "reserva_id"), inverseJoinColumns = @JoinColumn(name = "productos_producto_id"))
    private Set<Producto> productos = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "usuario_usuario_id")
    private Usuario usuario;

    private String observaciones;

    public Reserva() {
    }

    public Reserva(LocalDate fechaReserva, LocalDate fechaInicio, LocalDate fechaFin, Set<Producto> productos, Usuario usuario, String observaciones) {
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.productos = productos;
        this.usuario = usuario;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
