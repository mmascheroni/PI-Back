package com.backend.PIBack.dto;

import com.backend.PIBack.entity.Role;
import com.backend.PIBack.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {

    private Long id;

    private String nombre;

    private String apellido;

    @JsonIgnore
    private String password;

    private String email;

    private Role role;

    public UsuarioDto() {
    }

    public UsuarioDto(Long id, String nombre, String apellido, String password, String email, Role role) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static UsuarioDto fromUsuario(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getRole()

        );
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
