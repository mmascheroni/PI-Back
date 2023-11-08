package com.backend.PIBack.dto;

import com.backend.PIBack.entity.Caracteristica;
import com.backend.PIBack.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CaracteristicaDto {

    private Long id;

    private String nombre;

    private String urlIcono;

    private List<Producto> productos;

    public CaracteristicaDto() {
    }

    public CaracteristicaDto(Long id, String nombre, String urlIcono, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.urlIcono = urlIcono;
        this.productos = productos;
    }

    public static CaracteristicaDto fromCaracteristica(Caracteristica caracteristica) {

        return new CaracteristicaDto(
                caracteristica.getId(), caracteristica.getNombre(), caracteristica.getUrlIcono(), caracteristica.getProductos());
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

    public String getUrlIcono() {
        return urlIcono;
    }

    public void setUrlIcono(String urlIcono) {
        this.urlIcono = urlIcono;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
