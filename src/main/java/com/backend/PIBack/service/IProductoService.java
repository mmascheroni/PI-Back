package com.backend.PIBack.service;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.entity.Producto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {

    ProductoDto registrarProducto(Producto producto);

    ProductoDto buscarProductoPorId(Long id);

    List<ProductoDto> listarProductos();

    List<ProductoDto> listarProductosPaging(Pageable pageable);

    List<ProductoDto> listarProductosAleatorios(int limite);

    ProductoDto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);
}
