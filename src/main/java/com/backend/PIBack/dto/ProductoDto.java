package com.backend.PIBack.dto;

//import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Imagen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.backend.PIBack.entity.Producto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDto {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<String> imagenes;

    public ProductoDto() {
    }

    public ProductoDto(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public ProductoDto(Long id, String nombre, String descripcion, List<String> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagen(List<String> imagenes) {
        this.imagenes = imagenes;
    }


    public static List<String> obtenerUrls(List<Imagen> imagenes) {
        List<String> urls = imagenes
                .stream()
                .map(Imagen::getUrl) // Mapear cada Imagen a su URL
                .collect(Collectors.toList());

        return urls;
    }


    public static ProductoDto fromProducto(Producto producto) {
        List<String> urls = obtenerUrls(producto.getImagenes());
        return new ProductoDto(
                producto.getId(), producto.getNombre(), producto.getDescripcion(), urls
        );
    }

    @Override
    public String toString() {
            return "ProductoDto{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    ", imagenes='" + imagenes+ '\'' +
                    '}';
    }
}
