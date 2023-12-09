package com.backend.PIBack.service;

import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.entity.Reserva;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IReservaService {

    ReservaDto registrarReserva(Reserva reserva) throws MessagingException;

    List<ReservaDto> listarTodas();

    ReservaDto buscarReservaPorId(Long id);

    void eliminarReserva(Long id);

}
