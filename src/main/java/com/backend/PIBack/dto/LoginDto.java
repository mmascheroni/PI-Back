package com.backend.PIBack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LoginDto {

    private Long usuarioId;

    private String nombre;

    private String apellido;

    private String email;

    private String role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private String jwt;

    public LoginDto() {
    }

    public LoginDto(String email, String jwt) {
        this.email = email;
        this.jwt = jwt;
    }


    public LoginDto(String email, String password, String jwt) {
        this.email = email;
        this.password = password;
        this.jwt = jwt;
    }

    public LoginDto(Long usuarioId, String nombre, String apellido, String email, String role, String jwt) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.role = role;
        this.jwt = jwt;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public static LoginDto fromLogin(LoginDto loginDto) {
        return new LoginDto(loginDto.getEmail(), loginDto.getJwt());
    }
}
