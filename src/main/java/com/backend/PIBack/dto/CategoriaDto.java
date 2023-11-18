package com.backend.PIBack.dto;

import com.backend.PIBack.entity.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CategoriaDto {


    private Long id;

    private String titulo;

    private String descripcion;

    private String urlImagen;


    public CategoriaDto() {
    }

    public CategoriaDto(Long id, String titulo, String descripcion, String urlImagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;

    }

//    public static List<Producto> obtenerProductos(List<Producto> productos) {
//        List<Producto> productos1 = productos
//                .stream()
//                .map(Producto::getProductos)
//                .collect(Collectors.toList());
//
//        return productos1;
//    }

    public static CategoriaDto fromCategoria(Categoria categoria) {

        return new CategoriaDto(
                categoria.getId(), categoria.getTitulo(), categoria.getDescripcion(), categoria.getUrlImagen());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "Categoria {" +
                "id=" + id +
                ", nombre='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenes='" + urlImagen + '\'' +
                '}';
    }


}
