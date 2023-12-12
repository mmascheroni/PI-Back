package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.dto.ProductoImagenDto;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.repository.ImagenRepository;
import com.backend.PIBack.repository.ProductoRepository;
import com.backend.PIBack.service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoService implements IProductoService {


    private final static Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoRepository productoRepository;

    private final ImagenRepository imagenRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, ImagenRepository imagenRepository, ObjectMapper objectMapper) {
        this.productoRepository = productoRepository;
        this.imagenRepository = imagenRepository;
        this.objectMapper = objectMapper;
    }

    public static List<String> obtenerUrls(List<Imagen> imagenes) {
        List<String> urls = imagenes
                .stream()
                .map(Imagen::getUrl) // Mapear cada Imagen a su URL
                .collect(Collectors.toList());

        return urls;
    }


    @Override
    public ProductoDto registrarProducto(Producto producto) {
        // Guarda el producto para obtener su ID asignado
        Producto productoGuardado = productoRepository.save(producto);

        ProductoDto productoDto = objectMapper.convertValue(productoGuardado, ProductoDto.class);

        // Asocia las imágenes con el producto
        List<Imagen> imagenes = producto.getImagenes();
        if (imagenes != null && !imagenes.isEmpty()) {
            for (Imagen imagen : imagenes) {
                imagen.setProducto(productoGuardado);
            }
            imagenRepository.saveAll(imagenes);
        }

        // Devuelve el producto guardado con las imágenes asociadas
        return productoDto;
    }


    @Override
    public ProductoDto buscarProductoPorId(Long id) {
        Producto productoBuscado = productoRepository.findById(id).orElse(null);
        ProductoDto productoDto = null;

        if (productoBuscado != null) {
            productoDto = convertirAProductoDto(productoBuscado);
            LOGGER.info("Producto encontrado: {}", productoDto);
        } else {
            LOGGER.error("El producto buscado con id {}, no se encuentra registrado en la base de datos", id);
        }
        return productoDto;
    }


    @Override
    public List<ProductoDto> listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        ProductoDto productoDto;

        List<ProductoDto> productosDtos = productos.stream()
                .map(producto -> convertirAProductoDto(producto))
                .toList();

        if (productosDtos.size() > 0) {
            LOGGER.info("Listado de productos: {}", productosDtos);
        } else {
            LOGGER.warn("No existe producto registrado en la base de datos: {}", productosDtos);
        }

        return productosDtos;
    }


    @Override
    public List<ProductoDto> listarProductosPaging(Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);

        List<ProductoDto> productosDtos = productos.stream()
                .map(producto -> convertirAProductoDto(producto))
                .toList();

        if (productosDtos.size() > 0) {
            LOGGER.info("Listado de productos: {}", productosDtos);
        } else {
            LOGGER.warn("No existe producto registrado en la base de datos: {}", productosDtos);
        }

        return productosDtos;
    }

    @Override
    public List<ProductoDto> listarProductosAleatorios(int limite) {
        List<Producto> productos = productoRepository.listarProductosAleatorios(limite);

        List<ProductoDto> productosDtos = productos.stream()
                .map(producto -> convertirAProductoDto(producto))
                .toList();

        if (productosDtos.size() > 0) {
            LOGGER.info("Listado Aleatorio de productos: {}", productosDtos);
        } else {
            LOGGER.warn("No existe producto registrado en la base de datos: {}", productosDtos);
        }

        return productosDtos;
    }

    @Override
    public ProductoDto actualizarProducto(Producto producto) {
        Producto productoAActualizar = productoRepository.findById(producto.getId()).orElse(null);
        ProductoDto productoActualizadoDto = null;

        if (productoAActualizar != null) {
            if (producto.getNombre() != null) {
                productoAActualizar.setNombre(producto.getNombre());
            }

            if (producto.getDescripcion() != null) {
                productoAActualizar.setDescripcion(producto.getDescripcion());
            }

            if (producto.getImagenes() != null) {
                productoAActualizar.setImagenes(producto.getImagenes());
            }


            if (producto.getCategoria() != null) {
                productoAActualizar.setCategoria(producto.getCategoria());
            }

            if (producto.getCaracteristicas() != null) {
                productoAActualizar.setCaracteristicas(producto.getCaracteristicas());
            }

            productoActualizadoDto = objectMapper.convertValue(productoRepository.save(productoAActualizar), ProductoDto.class);

            LOGGER.info("El producto con ID {} ha sido actualizado: {}", producto.getId(), productoActualizadoDto);
        } else {
            LOGGER.warn("No es posible actualizar el producto porque no está registrado en la base de datos");
        }
        return productoActualizadoDto;
    }


    @Override
    public void eliminarProducto(Long id) {
        if (buscarProductoPorId(id) != null) {
            productoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado al producto con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el producto con id " + id);
        }
    }


    private ProductoDto convertirAProductoDto(Producto producto) {
        List<ProductoImagenDto> imagenesDto = producto.getImagenes()
                .stream()
                .map(imagen -> objectMapper.convertValue(imagen, ProductoImagenDto.class))
                .collect(Collectors.toList());

        return new ProductoDto(
                producto.getId(), producto.getNombre(), producto.getDescripcion(), imagenesDto, producto.getCategoria(), producto.getCaracteristicas()
        );
    }


}
