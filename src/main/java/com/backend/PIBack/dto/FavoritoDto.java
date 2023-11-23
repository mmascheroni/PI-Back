package com.backend.PIBack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoritoDto {

    private Long id;

    private UsuarioDto usuario;
    private ProductoDto producto;

    public FavoritoDto() {
    }

    public FavoritoDto(Long id, UsuarioDto usuario, ProductoDto producto) {
        this.id = id;
        this.usuario = usuario;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "FavoritoDto{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", producto=" + producto +
                '}';
    }
}
