package com.backend.PIBack.dto;

import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Producto;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ImagenDto {

    private Long id;

    private String url;

    private ProductoDto producto;

    public ImagenDto() {
    }

    public ImagenDto(String url) {
        this.url = url;
    }

    public ImagenDto(Long id, String url, ProductoDto producto) {
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

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

//    public static ImagenDto fromImagen(Imagen imagen) {
//        Long producto = imagen.getProducto().getId();
//        return new ImagenDto(imagen.getId(), imagen.getUrl(), producto);
//    }

    @Override
    public String toString() {
        return "ImagenDto{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", producto=" + producto +
                '}';
    }
}
