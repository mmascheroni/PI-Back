package com.backend.PIBack.controller;

import com.backend.PIBack.dto.CategoriaDto;
import com.backend.PIBack.entity.Categoria;
import com.backend.PIBack.service.impl.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<CategoriaDto> registarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.registrarCategoria(categoria));
    }

    //GET
    @GetMapping
    public List<CategoriaDto> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable Long id) {
        CategoriaDto CategoriaEncontrada = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(CategoriaEncontrada);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<CategoriaDto> buscarCategoriaPorTitulo(@PathVariable String titulo) {
        CategoriaDto CategoriaEncontrada = categoriaService.buscarCategoriaPorTitulo(titulo);
        return ResponseEntity.ok(CategoriaEncontrada);
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<CategoriaDto> actualizarCategoria(@RequestBody Categoria categoria) {
        CategoriaDto categoriaActualizada = categoriaService.actualizarCategoria(categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("Se ha eliminado la categoría.");
    }


}
