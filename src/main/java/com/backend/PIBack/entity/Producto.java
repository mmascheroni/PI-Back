package com.backend.PIBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private List<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "producto_caracteristica",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
    )
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Favorito> favoritos;

    @JsonIgnore
    @ManyToMany(mappedBy = "productos")
    private Set<Reserva> reservas = new LinkedHashSet<>();


    public Producto() {
    }


    public Producto(String nombre, String descripcion, List<Imagen> imagenes, Categoria categoria, List<Caracteristica> caracteristicas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
    }

    public Producto(String nombre, String descripcion, List<Imagen> imagenes, Categoria categoria, List<Caracteristica> caracteristicas, List<Favorito> favoritos, Set<Reserva> reservas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.favoritos = favoritos;
        this.reservas = reservas;
    }

    public Producto(String nombre, String descripcion, Categoria categoria, List<Caracteristica> caracteristicas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
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

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(getId(), producto.getId()) && Objects.equals(getNombre(), producto.getNombre()) && Objects.equals(getDescripcion(), producto.getDescripcion()) && Objects.equals(getImagenes(), producto.getImagenes()) && Objects.equals(getCategoria(), producto.getCategoria()) && Objects.equals(getCaracteristicas(), producto.getCaracteristicas()) && Objects.equals(getFavoritos(), producto.getFavoritos()) && Objects.equals(getReservas(), producto.getReservas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getDescripcion(), getImagenes(), getCategoria(), getCaracteristicas(), getFavoritos(), getReservas());
    }
}
