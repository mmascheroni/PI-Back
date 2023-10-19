package com.backend.PIBack.service.impl;

import com.backend.PIBack.service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.PIBack.dto.ProductoDto;

import com.backend.PIBack.entity.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.PIBack.repository.ProductoRepository;

import java.util.List;


@Service
public class ProductoService implements IProductoService {


    private final static Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoRepository productoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, ObjectMapper objectMapper) {
        this.productoRepository = productoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ProductoDto registrarProducto(Producto producto) {
        ProductoDto productoDto = objectMapper.convertValue(productoRepository.save(producto), ProductoDto.class);
        LOGGER.info("Se guardó el producto: {}", productoDto);
        return productoDto;
    }

    @Override
    public ProductoDto buscarProductoPorId(Long id) {
        Producto productoBuscado = productoRepository.findById(id).orElse(null);
        ProductoDto productoDto = null;

        if ( productoBuscado != null ) {
            productoDto = objectMapper.convertValue(productoBuscado, ProductoDto.class);
            LOGGER.info("Producto encontrado: {}", productoDto);
        } else {
            LOGGER.error("El producto buscado con id {}, no se encuentra registrado en la base de datos", id);
        }
        return productoDto;
    }

    @Override
    public List<ProductoDto> listarProductos() {
        List<Producto> productos = productoRepository.findAll();

        List<ProductoDto> productosDtos = productos.stream().map(producto -> {


            return new ProductoDto(producto.getId(), producto.getNombre(), producto.getDescripcion());
        }).toList();

        if ( productosDtos.size() > 0 ) {
            LOGGER.info("Listado de productos: {}", productosDtos);
        } else {
            LOGGER.warn("No existe paciente registrado en la base de datos: {}", productosDtos);
        }

        return productosDtos;
    }

//    @Override
//    public ProductoDto actualizarProducto(Producto producto) {
//        return null;
//    }

    @Override
    public void eliminarProducto(Long id) {
        if (buscarProductoPorId(id) != null) {
            productoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado al producto con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
        }
    }
}
