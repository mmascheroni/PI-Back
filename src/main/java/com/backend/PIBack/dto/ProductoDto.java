package com.backend.PIBack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.backend.PIBack.entity.Producto;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDto {

    private Long id;

    private String nombre;

    private String descripcion;

    public ProductoDto() {
    }

    public ProductoDto(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static ProductoDto fromProducto(Producto producto) {
        return new ProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion()
        );
    }

    @Override
    public String toString() {
        return "ProductoDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
