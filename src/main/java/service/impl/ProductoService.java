package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ProductoDto;

import entity.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;
import service.IProductoService;

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


//    @Override
//    public ProductoDto buscarProductoPorId(Long id) {
//        return null;
//    }

    @Override
    public List<ProductoDto> listarProductos() {
        return null;
    }

    @Override
    public ProductoDto registrarProducto(Producto producto) {
        return null;
    }

//    @Override
//    public ProductoDto actualizarProducto(Producto producto) {
//        return null;
//    }

    @Override
    public void eliminarProducto(Long id) {

    }
}
