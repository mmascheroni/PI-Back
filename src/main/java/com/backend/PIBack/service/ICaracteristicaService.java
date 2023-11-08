package com.backend.PIBack.service;

import com.backend.PIBack.dto.CaracteristicaDto;
import com.backend.PIBack.entity.Caracteristica;

import java.util.List;

public interface ICaracteristicaService {

    CaracteristicaDto registrarCaracteristica(Caracteristica caracteristica);

    CaracteristicaDto buscarCaracteristicaPorId(Long id);

    CaracteristicaDto buscarCaracteristicaPorNombre(String nombre);

    List<CaracteristicaDto> listarCaracteristicas();

    CaracteristicaDto actualizarCaracteristica(Caracteristica caracteristica);

    void eliminarCaracteristica(Long id);
}
