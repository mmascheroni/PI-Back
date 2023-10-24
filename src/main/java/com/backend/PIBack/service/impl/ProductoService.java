package com.backend.PIBack.service.impl;

import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.PIBack.dto.ProductoDto;

import com.backend.PIBack.entity.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.PIBack.repository.ProductoRepository;

import java.util.List;
import java.util.stream.Collectors;


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

        ProductoDto productoDto1 = new ProductoDto(productoDto.getId(), productoDto.getNombre(), productoDto.getDescripcion());
        return productoDto1;
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

    public static List<String> obtenerUrls(List<Imagen> imagenes) {
        List<String> urls = imagenes
                .stream()
                .map(Imagen::getUrl) // Mapear cada Imagen a su URL
                .collect(Collectors.toList());

        return urls;
    }


    @Override
    public List<ProductoDto> listarProductos(Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);

        List<ProductoDto> productosDtos = productos.stream().map(producto -> {
            List<String> urls = obtenerUrls(producto.getImagenes());
            return new ProductoDto(producto.getId(), producto.getNombre(), producto.getDescripcion(), urls);
        }).toList();

        if ( productosDtos.size() > 0 ) {
            LOGGER.info("Listado de productos: {}", productosDtos);
        } else {
            LOGGER.warn("No existe producto registrado en la base de datos: {}", productosDtos);
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
