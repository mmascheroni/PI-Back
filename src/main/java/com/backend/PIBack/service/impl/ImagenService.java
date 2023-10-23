package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.ImagenDto;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.repository.ImagenRepository;
import com.backend.PIBack.service.IImagenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenService implements IImagenService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);
    private final ImagenRepository imagenRepository;
    private final ObjectMapper objectMapper;

    public ImagenService(ImagenRepository imagenRepository, ObjectMapper objectMapper) {
        this.imagenRepository = imagenRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public ImagenDto registrarImagen(Imagen imagen) {
        ImagenDto imagenDto = objectMapper.convertValue(imagenRepository.save(imagen), ImagenDto.class);
        LOGGER.info("Se guardó la imagen: {}", imagenDto);
        return imagenDto;
    }

    @Override
    public ImagenDto buscarImagenPorId(Long id) {
        Imagen imagenBuscado = imagenRepository.findById(id).orElse(null);
        ImagenDto imagenDto = null;

        if ( imagenBuscado != null ) {
            imagenDto = objectMapper.convertValue(imagenBuscado, ImagenDto.class);
            LOGGER.info("Imagen encontrada: {}", imagenDto);
        } else {
            LOGGER.error("La imagen buscada con id {}, no se encuentra registrada en la base de datos", id);
        }
        return imagenDto;
    }

    @Override
    public List<ImagenDto> listarImagenes() {
        List<Imagen> imagenes = imagenRepository.findAll();

        List<ImagenDto> imagenesDtos = imagenes.stream().map(imagen -> {


            return new ImagenDto(imagen.getId(), imagen.getUrl(), imagen.getProducto());
        }).toList();

        if ( imagenesDtos.size() > 0 ) {
            LOGGER.info("Listado de imagenes: {}", imagenesDtos);
        } else {
            LOGGER.warn("No existe imagenes registradas en la base de datos: {}", imagenesDtos);
        }

        return imagenesDtos;
    }

    @Override
    public void eliminarImagen(Long id) {
        if (buscarImagenPorId(id) != null) {
            imagenRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la imagen con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la iamgen con id " + id);
        }
    }
}
