package com.backend.PIBack.service;

import com.backend.PIBack.dto.ImagenDto;
import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Producto;

import java.util.List;

public interface IImagenService {

    ImagenDto registrarImagen(Imagen imagen);

    ImagenDto buscarImagenPorId(Long id);

    List<ImagenDto> listarImagenes();

    ImagenDto actualizarImagen(Imagen imagen);

    void eliminarImagen(Long id);
}
