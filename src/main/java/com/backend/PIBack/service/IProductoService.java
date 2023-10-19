package com.backend.PIBack.service;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.entity.Producto;

import java.util.List;

public interface IProductoService {

    ProductoDto buscarProductoPorId(Long id);

    List<ProductoDto> listarProductos();

    ProductoDto registrarProducto(Producto producto);

    // ProductoDto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);
}
