package com.backend.PIBack.controller;

import com.backend.PIBack.dto.FavoritoDto;
import com.backend.PIBack.entity.Favorito;
import com.backend.PIBack.service.impl.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private FavoritoService favoritoService;

    @Autowired
    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }


    @PostMapping("/registrar")
    public ResponseEntity<FavoritoDto> registarFavorito(@RequestBody Favorito favorito) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.registrarFavorito(favorito));
    }

    //GET
    @GetMapping
    public List<FavoritoDto> listarFavoritos() {
        return favoritoService.listarFavoritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoritoDto> buscarFavoritoPorId(@PathVariable Long id) {
        FavoritoDto favoritoEncontrado = favoritoService.buscarFavoritoPorId(id);
        return ResponseEntity.ok(favoritoEncontrado);
    }

    //GET
    @GetMapping("/usuario/id/{id}")
    public ResponseEntity<List<FavoritoDto>> listarFavoritosPorUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(favoritoService.obtenerProductosFavoritosDeUsuarioPorId(id));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<FavoritoDto>> listarFavoritosPorUsuarioPorEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(favoritoService.obtenerProductosFavoritosDeUsuarioPorEmail(email));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<FavoritoDto> actualizarFavorito(@RequestBody Favorito favorito) {
        FavoritoDto favoritoActualizada = favoritoService.actualizarFavorito(favorito);
        return ResponseEntity.ok(favoritoActualizada);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarFavorito(@PathVariable Long id) {
        favoritoService.eliminarFavorito(id);
        return ResponseEntity.ok("Se ha eliminado favorito.");
    }
}
