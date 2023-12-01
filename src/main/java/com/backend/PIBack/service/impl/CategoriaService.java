package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.CategoriaDto;
import com.backend.PIBack.entity.Categoria;
import com.backend.PIBack.repository.CategoriaRepository;
import com.backend.PIBack.service.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, ObjectMapper objectMapper) {
        this.categoriaRepository = categoriaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CategoriaDto registrarCategoria(Categoria categoria) {
        CategoriaDto categoriaDto = objectMapper.convertValue(categoriaRepository.save(categoria), CategoriaDto.class);
        LOGGER.info("Se guardó la categoría: {}", categoriaDto);
        return categoriaDto;
    }

    @Override
    public CategoriaDto buscarCategoriaPorId(Long id) {
        Categoria categoriaBuscada = categoriaRepository.findById(id).orElse(null);
        CategoriaDto categoriaDto = null;

        if (categoriaBuscada != null) {
            categoriaDto = objectMapper.convertValue(categoriaBuscada, CategoriaDto.class);
            LOGGER.info("Categoría encontrada: {}", categoriaDto);
        } else {
            LOGGER.error("La categoría buscada con id {}, no se encuentra registrada en la base de datos", id);
        }
        return categoriaDto;
    }

    @Override
    public CategoriaDto buscarCategoriaPorTitulo(String titulo) {
        Categoria categoriaBuscada = categoriaRepository.findCategoriaByTitulo(titulo).orElse(null);
        CategoriaDto categoriaDto = null;

        if (categoriaBuscada != null) {
            categoriaDto = objectMapper.convertValue(categoriaBuscada, CategoriaDto.class);
            LOGGER.info("Categoría encontrada: {}", categoriaDto);
        } else {
            LOGGER.error("La categoría con el título {}, no se encuentra registrada en la base de datos", titulo);
        }
        return categoriaDto;
    }

    @Override
    public List<CategoriaDto> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();

        List<CategoriaDto> categoriaDtos = categorias.stream().map(categoria -> {

            return new CategoriaDto(categoria.getId(), categoria.getTitulo(), categoria.getDescripcion(), categoria.getUrlImagen());
        }).toList();

        if (categoriaDtos.size() > 0) {
            LOGGER.info("Listado de categorías: {}", categoriaDtos);
        } else {
            LOGGER.warn("No existen categorías registradas en la base de datos: {}", categoriaDtos);
        }

        return categoriaDtos;
    }

    @Override
    public CategoriaDto actualizarCategoria(Categoria categoria) {
        Categoria categoriaAActualizar = categoriaRepository.findById(categoria.getId()).orElse(null);
        CategoriaDto categoriaActualizadaDto = null;

        if (categoriaAActualizar != null) {

            if (categoria.getTitulo() != null) {
                categoriaAActualizar.setTitulo(categoria.getTitulo());
            }

            if (categoria.getDescripcion() != null) {
                categoriaAActualizar.setDescripcion(categoria.getDescripcion());
            }

            if (categoria.getUrlImagen() != null) {
                categoriaAActualizar.setUrlImagen(categoria.getUrlImagen());
            }

            categoriaActualizadaDto = objectMapper.convertValue(registrarCategoria(categoriaAActualizar), CategoriaDto.class);
            LOGGER.warn("La categoría con ID {} ha sido actualizada: {}", categoria.getId(), categoriaActualizadaDto);

        } else {

            LOGGER.warn("No es posible actualizar la categoría porque no está registrada en la base de datos");

        }

        return categoriaActualizadaDto;
    }

    @Override
    public void eliminarCategoria(Long id) {

        if (buscarCategoriaPorId(id) != null) {
            categoriaRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la categoría con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la categoría con id " + id);
        }
    }

}
