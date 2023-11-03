package com.backend.PIBack.service;

import com.backend.PIBack.dto.CategoriaDto;
import com.backend.PIBack.entity.Categoria;

import java.util.List;

public interface ICategoriaService {


    CategoriaDto registrarCategoria(Categoria categoria);

    CategoriaDto buscarCategoriaPorId(Long id);

    CategoriaDto buscarCategoriaPorTitulo(String titulo);

    List<CategoriaDto> listarCategorias();

    CategoriaDto actualizarCategoria(Categoria categoria);

    void eliminarCategoria(Long id);
}
