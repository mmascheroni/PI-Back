package com.backend.PIBack.controller;


import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.service.impl.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/producto")
public class ProductoController {

    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<ProductoDto> registarProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.registrarProducto(producto));
    }

    //GET

    @GetMapping
    public List<ProductoDto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarProductoPorId(@PathVariable Long id) {
        ProductoDto productoEncontrado = productoService.buscarProductoPorId(id);
        return ResponseEntity.ok(productoEncontrado);
    }


    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Se ha eliminado el producto ");
    }
}