package com.backend.PIBack.service;


import com.backend.PIBack.dto.FavoritoDto;
import com.backend.PIBack.entity.Favorito;

import java.util.List;

public interface IFavoritoService {

    FavoritoDto registrarFavorito(Favorito favorito);

    FavoritoDto buscarFavoritoPorId(Long id);

    List<FavoritoDto> obtenerProductosFavoritosDeUsuarioPorId(Long usuarioId);

    List<FavoritoDto> obtenerProductosFavoritosDeUsuarioPorEmail(String usuarioEmail);

    List<FavoritoDto> listarFavoritos();

    FavoritoDto actualizarFavorito(Favorito favorito);

    void eliminarFavorito(Long id);
}
