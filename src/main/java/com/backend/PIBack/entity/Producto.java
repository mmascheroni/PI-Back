package com.backend.PIBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    //validación nombre único
    private String nombre;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
    @JsonIgnore
    private List<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Producto() {
    }


    public Producto(String nombre, String descripcion, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Producto(String nombre, String descripcion, List<Imagen> imagenes, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
