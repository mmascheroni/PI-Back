
package com.backend.PIBack.dto;

import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenDto {

    private Long id;

    private String url;

    private Producto producto;

    public ImagenDto() {
    }

    public ImagenDto(Long id, String url, Producto producto) {
        this.id = id;
        this.url = url;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


}
