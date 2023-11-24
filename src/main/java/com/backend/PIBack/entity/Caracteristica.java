package com.backend.PIBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CARACTERISTICAS")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String urlIcono;

    @ManyToMany(mappedBy = "caracteristicas")
    @JsonIgnore
    private List<Producto> productos;

    public Caracteristica() {
    }

    public Caracteristica(String nombre, String urlIcono, List<Producto> productos) {
        this.nombre = nombre;
        this.urlIcono = urlIcono;
        this.productos = productos;
    }

    public Long getId() {
        return id;
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
