package com.backend.PIBack.service;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.entity.Producto;

import java.util.List;

public interface IProductoService {

    ProductoDto registrarProducto(Producto producto);

    ProductoDto buscarProductoPorId(Long id);

    List<ProductoDto> listarProductos();

    // ProductoDto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);
}
