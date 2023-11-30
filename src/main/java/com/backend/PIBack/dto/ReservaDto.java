package com.backend.PIBack.dto;

import com.backend.PIBack.entity.Reserva;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReservaDto {

    private Long id;
    private LocalDate fechaReserva = LocalDate.now();
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Set<ProductoDto> productos = new LinkedHashSet<>();
    private UsuarioDto usuario;
    private String observaciones;

    public ReservaDto() {
    }

    public ReservaDto(Long id, LocalDate fechaReserva, LocalDate fechaInicio, LocalDate fechaFin, Set<ProductoDto> productos, UsuarioDto usuario, String observaciones) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.productos = productos;
        this.usuario = usuario;
        this.observaciones = observaciones;
    }

    public static ReservaDto fromReserva(Reserva reserva){
        Set<ProductoDto> productoDtos = reserva.getProductos().stream()
                .map(ProductoDto::fromProducto)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        UsuarioDto usuarioDto = UsuarioDto.fromUsuario(reserva.getUsuario());

        return new ReservaDto(
                reserva.getId(),
                reserva.getFechaReserva(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                productoDtos,
                usuarioDto,
                reserva.getObservaciones()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<ProductoDto> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoDto> productos) {
        this.productos = productos;
    }


    public ReservaDto(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
