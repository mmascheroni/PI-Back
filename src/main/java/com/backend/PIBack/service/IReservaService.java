package com.backend.PIBack.service;

import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.entity.Reserva;

public interface IReservaService {

    ReservaDto registrarReserva (Reserva reserva);
}
