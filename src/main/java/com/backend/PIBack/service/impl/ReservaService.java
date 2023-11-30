package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.entity.Reserva;
import com.backend.PIBack.repository.ReservaRepository;
import com.backend.PIBack.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;


    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;

    }


    @Override
    public ReservaDto registrarReserva(Reserva reserva) {
        Reserva reservaGuardada = reservaRepository.save(reserva);

        return ReservaDto.fromReserva(reservaGuardada);
    }
}
