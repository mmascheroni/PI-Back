package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.CaracteristicaDto;
import com.backend.PIBack.entity.Caracteristica;
import com.backend.PIBack.repository.CaracteristicaRepository;
import com.backend.PIBack.service.ICaracteristicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicaService implements ICaracteristicaService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CaracteristicaService.class);
    private final CaracteristicaRepository caracteristicaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository, ObjectMapper objectMapper) {
        this.caracteristicaRepository = caracteristicaRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public CaracteristicaDto registrarCaracteristica(Caracteristica caracteristica) {
        CaracteristicaDto caracteristicaDto = objectMapper.convertValue(caracteristicaRepository.save(caracteristica), CaracteristicaDto.class);
        LOGGER.info("Se guardó la característica: {}", caracteristicaDto);
        return caracteristicaDto;
    }

    @Override
    public CaracteristicaDto buscarCaracteristicaPorId(Long id) {
        Caracteristica caracteristicaBuscada = caracteristicaRepository.findById(id).orElse(null);
        CaracteristicaDto caracteristicaDto = null;

        if (caracteristicaBuscada != null) {
            caracteristicaDto = objectMapper.convertValue(caracteristicaBuscada, CaracteristicaDto.class);
            LOGGER.info("Característica encontrada: {}", caracteristicaDto);
        } else {
            LOGGER.error("La característica buscada con id {}, no se encuentra registrada en la base de datos", id);
        }
        return caracteristicaDto;
    }

    @Override
    public CaracteristicaDto buscarCaracteristicaPorNombre(String nombre) {
        Caracteristica caracteristicaBuscada = caracteristicaRepository.findCaracteristicaByNombre(nombre).orElse(null);
        CaracteristicaDto caracteristicaDto = null;

        if (caracteristicaBuscada != null) {
            caracteristicaDto = objectMapper.convertValue(caracteristicaBuscada, CaracteristicaDto.class);
            LOGGER.info("Característica encontrada: {}", caracteristicaDto);
        } else {
            LOGGER.error("La característica con el título {}, no se encuentra registrada en la base de datos", nombre);
        }
        return caracteristicaDto;
    }

    @Override
    public List<CaracteristicaDto> listarCaracteristicas() {
        List<Caracteristica> caracteristicas = caracteristicaRepository.findAll();

        List<CaracteristicaDto> caracteristicaDtos = caracteristicas.stream().map(caracteristica -> {

            return new CaracteristicaDto(caracteristica.getId(), caracteristica.getNombre(), caracteristica.getUrlIcono(), caracteristica.getProductos());
        }).toList();

        if (caracteristicaDtos.size() > 0) {
            LOGGER.info("Listado de características: {}", caracteristicaDtos);
        } else {
            LOGGER.warn("No existen características registradas en la base de datos: {}", caracteristicaDtos);
        }

        return caracteristicaDtos;
    }

    @Override
    public CaracteristicaDto actualizarCaracteristica(Caracteristica caracteristica) {
        Caracteristica caracteristicaAActualizar = caracteristicaRepository.findById(caracteristica.getId()).orElse(null);
        CaracteristicaDto caracteristicaActualizadaDto = null;

        if (caracteristicaAActualizar != null) {

            if (caracteristica.getNombre() != null) {
                caracteristicaAActualizar.setNombre(caracteristica.getNombre());
            }

            if (caracteristica.getUrlIcono() != null) {
                caracteristicaAActualizar.setUrlIcono(caracteristica.getUrlIcono());
            }

            if (caracteristica.getProductos() != null) {
                caracteristicaAActualizar.setProductos(caracteristica.getProductos());
            }

            caracteristicaActualizadaDto = objectMapper.convertValue(caracteristicaRepository.save(caracteristicaAActualizar), CaracteristicaDto.class);

            LOGGER.warn("La característica con ID {} ha sido actualizada: {}", caracteristica.getId(), caracteristicaActualizadaDto);

            return caracteristicaActualizadaDto;

        } else {
            LOGGER.warn("No es posible actualizar la característica porque no está registrada en la base de datos");
            return null;
        }
    }

    @Override
    public void eliminarCaracteristica(Long id) {
        if (buscarCaracteristicaPorId(id) != null) {
            caracteristicaRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la característica con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado la característica con id " + id);
        }
    }
}
