package service;

import dto.ProductoDto;
import entity.Producto;

import java.util.List;

public interface IProductoService {

    // ProductoDto buscarProductoPorId(Long id);

    List<ProductoDto> listarProductos();

    ProductoDto registrarProducto(Producto producto);

    // ProductoDto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);
}
