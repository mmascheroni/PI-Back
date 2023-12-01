package com.backend.PIBack.dto;

//import com.backend.PIBack.entity.Imagen;

import com.backend.PIBack.entity.Caracteristica;
import com.backend.PIBack.entity.Categoria;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDto {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<ProductoImagenDto> imagenes;

    private Categoria categoria;

    private List<Caracteristica> caracteristicas;

    @Autowired
    private static ObjectMapper objectMapper;

    public ProductoDto() {
    }

    public ProductoDto(Long id, String nombre, String descripcion, Categoria categoria, List<Caracteristica> caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
    }

    public ProductoDto(Long id, String nombre, String descripcion, List<ProductoImagenDto> imagenes, Categoria categoria, List<Caracteristica> caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
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
    public List<ProductoImagenDto> getImagenes() {
        return imagenes;
    }

    public void setImagen(List<ProductoImagenDto> imagenes) {
        this.imagenes = imagenes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setImagenes(List<ProductoImagenDto> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public static List<String> obtenerUrls(List<Imagen> imagenes) {
        List<String> urls = imagenes
                .stream()
                .map(Imagen::getUrl) // Mapear cada Imagen a su URL
                .collect(Collectors.toList());

        return urls;
    }


    public static ProductoDto fromProducto(Producto producto) {
        List<ProductoImagenDto> imagenesDto = producto.getImagenes()
                .stream()
                .map(imagen -> objectMapper.convertValue(imagen, ProductoImagenDto.class))
                .collect(Collectors.toList());

        return new ProductoDto(
                producto.getId(), producto.getNombre(), producto.getDescripcion(), imagenesDto, producto.getCategoria(), producto.getCaracteristicas()
        );
    }


    @Override
    public String toString() {
        return "Producto {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenes='" + imagenes + '\'' +
                ", categoría='" + categoria + '\'' +
                ", características='" + caracteristicas + '\'' +
                '}';
    }
}
