package com.backend.PIBack.controller;


import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.service.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/producto")
public class ProductoController {

    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //POST
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<ProductoDto> registrarProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.registrarProducto(producto));
    }

    //GET
    @GetMapping
    public List<ProductoDto> listarProductos() {
        return productoService.listarProductos();
    }


    @GetMapping("/paging")
    public List<ProductoDto> listarProductosPaging(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return productoService.listarProductosPaging(pageable);
    }

    @GetMapping("/random")
    public List<ProductoDto> listarProductosAleatorios(@RequestParam(name = "limite") int limite) {
        return productoService.listarProductosAleatorios(limite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarProductoPorId(@PathVariable Long id) {
        ProductoDto productoEncontrado = productoService.buscarProductoPorId(id);
        return ResponseEntity.ok(productoEncontrado);
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<ProductoDto> actualizarProducto(@RequestBody Producto producto) {
        ProductoDto productoActualizado = productoService.actualizarProducto(producto);
        return ResponseEntity.ok(productoActualizado);
    }


    //DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Se ha eliminado el producto ");
    }
}
