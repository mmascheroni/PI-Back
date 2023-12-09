package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.entity.Reserva;
import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.mailSender.EmailService;
import com.backend.PIBack.repository.ReservaRepository;
import com.backend.PIBack.repository.UsuarioRepository;
import com.backend.PIBack.service.IReservaService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaService.class);
    private final ReservaRepository reservaRepository;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ProductoService productoService, UsuarioService usuarioService, EmailService emailService, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.emailService = emailService;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public ReservaDto registrarReserva(Reserva reserva) throws MessagingException {
        ReservaDto reservaDto = null;
        Set<ProductoDto> productosDto = new HashSet<>();

        for (Producto producto : reserva.getProductos()) {
            ProductoDto productoDto = productoService.buscarProductoPorId(producto.getId());

            productosDto.add(productoDto);
        }

        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId()).orElse(null);
        UsuarioDto usuarioDto = usuarioService.buscarUsuarioPorId(reserva.getUsuario().getId());


        if (productosDto.isEmpty() || usuarioDto == null) {
            if (productosDto.isEmpty() && usuarioDto == null) {
                System.out.println("El producto o el usuario no se encuentran en la base de datos");
            } else if (productosDto.isEmpty()) {
                System.out.println("El producto no se encuentran en la base de datos");
            } else {
                System.out.println("El usuario no se encuentran en la base de datos");
            }
        } else {
            reservaDto = ReservaDto.fromReserva(reservaRepository.save(reserva));
            reservaDto.setProductos(productosDto);
            reservaDto.setUsuario(usuarioDto);
            System.out.println("La reserva ha sido registrada con éxito");

            emailService.sendWithImageFromURLReserva("pi.sinfonia23@gmail.com", usuarioDto.getEmail(), "Confirmación Reserva Exitosa", usuario, productosDto, reservaDto);
        }

        return reservaDto;
    }

    @Override
    public List<ReservaDto> listarTodas() {
        return reservaRepository.findAll().stream().map(ReservaDto::fromReserva).collect(Collectors.toList());

    }

    @Override
    public ReservaDto buscarReservaPorId(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.map(ReservaDto::fromReserva).orElse(null);

    }

    @Override
    public void eliminarReserva(Long id) {
        if (buscarReservaPorId(id) != null) {
            reservaRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado la reserva con ID: {}", id);
        } else {
            LOGGER.warn("La reserva con ID: " + id + "no se encuentra en la base de datos");

        }
    }


}
