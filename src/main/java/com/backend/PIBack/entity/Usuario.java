package com.backend.PIBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Favorito> favoritos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private Set<Reserva> reservas = new LinkedHashSet<>();

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String password, Role role) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Usuario(String nombre, String apellido, String email, String password, Role role, List<Favorito> favoritos, Set<Reserva> reservas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.role = role;
        this.favoritos = favoritos;
        this.reservas = reservas;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getId(), usuario.getId()) && Objects.equals(getNombre(), usuario.getNombre()) && Objects.equals(getApellido(), usuario.getApellido()) && Objects.equals(getEmail(), usuario.getEmail()) && Objects.equals(getPassword(), usuario.getPassword()) && getRole() == usuario.getRole() && Objects.equals(getFavoritos(), usuario.getFavoritos()) && Objects.equals(getReservas(), usuario.getReservas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellido(), getEmail(), getPassword(), getRole(), getFavoritos(), getReservas());
    }
}
