package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.entity.Reserva;
import com.backend.PIBack.repository.ReservaRepository;
import com.backend.PIBack.service.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final ObjectMapper mapper;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ProductoService productoService, UsuarioService usuarioService, ObjectMapper mapper) {
        this.reservaRepository = reservaRepository;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }



//    @Override
//    public ReservaDto registrarReserva(Reserva reserva) {
//        Reserva reservaGuardada = reservaRepository.save(reserva);
//
//        return ReservaDto.fromReserva(reservaGuardada);
//    }

    @Override
    public ReservaDto registrarReserva(Reserva reserva) {
        ReservaDto reservaDto = null;
        Set<ProductoDto> productosDto = new HashSet<>();

        for (Producto producto : reserva.getProductos()) {
            ProductoDto productoDto = productoService.buscarProductoPorId(producto.getId());
            productosDto.add(productoDto);
        }

        UsuarioDto usuario = usuarioService.buscarUsuarioPorId(reserva.getUsuario().getId());

        if(productosDto.isEmpty() || usuario == null){
            if(productosDto.isEmpty() && usuario == null){
                System.out.println("El producto o el usuario no se encuentran en la base de datos");
            }else if (productosDto.isEmpty()) {
                System.out.println("El producto no se encuentran en la base de datos");
            }else {
                System.out.println("El usuario no se encuentran en la base de datos");
            }
        }else {
            reservaDto = ReservaDto.fromReserva(reservaRepository.save(reserva));
            System.out.println("La reserva ha sido registrada con éxito");
        }

        return reservaDto;
    }

    @Override
    public List<ReservaDto> listarTodas() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDto> reservaDtos = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDto reservaDto = mapper.convertValue(reserva, ReservaDto.class);
            Set<ProductoDto> productosDto = new HashSet<>();

            for (Producto producto : reserva.getProductos()) {
                productosDto.add(ProductoDto.fromProducto(producto));
            }

            reservaDto.setProductos(productosDto);
            reservaDto.setUsuario(UsuarioDto.fromUsuario(reserva.getUsuario()));
            reservaDtos.add(reservaDto);
        }

        return reservaDtos;
    }

    @Override
    public ReservaDto buscarReservaPorId(Reserva reserva) {
        return null;
    }
}
