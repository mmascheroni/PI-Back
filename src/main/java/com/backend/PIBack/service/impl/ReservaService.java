package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.dto.ReservaDto;
import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.entity.Reserva;
import com.backend.PIBack.repository.ReservaRepository;
import com.backend.PIBack.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;


    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ProductoService productoService, UsuarioService usuarioService) {
        this.reservaRepository = reservaRepository;
        this.productoService = productoService;
        this.usuarioService = usuarioService;

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
            productoDto.setId(producto.getId());
            productoDto.setNombre(producto.getNombre());
            productoDto.setDescripcion(producto.getDescripcion());
            productoDto.setCategoria(producto.getCategoria());
            productoDto.setCaracteristicas(producto.getCaracteristicas());

            productosDto.add(productoDto);
        }

        UsuarioDto usuarioDto = usuarioService.buscarUsuarioPorId(reserva.getUsuario().getId());
        usuarioDto.setId(reserva.getUsuario().getId());
        usuarioDto.setNombre(reserva.getUsuario().getNombre());
        usuarioDto.setApellido(reserva.getUsuario().getNombre());
        usuarioDto.setEmail(reserva.getUsuario().getEmail());


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
        }

        return reservaDto;
    }
    @Override
    public List<ReservaDto> listarTodas() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDto> reservaDtos = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDto reservaDto = new ReservaDto();
            reservaDto.setId(reserva.getId());
            reservaDto.setFechaReserva(reserva.getFechaReserva());
            reservaDto.setFechaFin(reserva.getFechaFin());
            reservaDto.setObservaciones(reserva.getObservaciones());

            Set<ProductoDto> productosDto = new HashSet<>();
            for (Producto producto : reserva.getProductos()) {
                ProductoDto productoDto = new ProductoDto();
                productoDto.setId(producto.getId());
                productoDto.setNombre(producto.getNombre());
                productoDto.setDescripcion(producto.getDescripcion());
                productoDto.setCategoria(producto.getCategoria());
                productoDto.setCaracteristicas(producto.getCaracteristicas());

                productosDto.add(productoDto);
            }

            reservaDto.setProductos(productosDto);

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(reserva.getUsuario().getId());
            usuarioDto.setNombre(reserva.getUsuario().getNombre());
            usuarioDto.setApellido(reserva.getUsuario().getApellido());
            usuarioDto.setEmail(reserva.getUsuario().getEmail());

            reservaDto.setUsuario(usuarioDto);

            reservaDtos.add(reservaDto);
        }

        return reservaDtos;
    }

    @Override
    public ReservaDto buscarReservaPorId(Long id) {
        Reserva reservabuscada = reservaRepository.findById(id).orElse(null);
        ReservaDto reservaDto = null;

        if (reservabuscada != null) {
            reservaDto = new ReservaDto();
            reservaDto.setId(reservabuscada.getId());
            reservaDto.setFechaReserva(reservabuscada.getFechaReserva());
            reservaDto.setFechaInicio(reservabuscada.getFechaInicio());
            reservaDto.setFechaFin(reservabuscada.getFechaFin());
            reservaDto.setObservaciones(reservabuscada.getObservaciones());

            Set<ProductoDto> productosDto = new HashSet<>();
            for (Producto producto : reservabuscada.getProductos()) {
                ProductoDto productoDto = new ProductoDto();
                productoDto.setId(producto.getId());
                productoDto.setNombre(producto.getNombre());
                productoDto.setDescripcion(producto.getDescripcion());
                productoDto.setCategoria(producto.getCategoria());
                productoDto.setCaracteristicas(producto.getCaracteristicas());

                productosDto.add(productoDto);
            }

            reservaDto.setProductos(productosDto);

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(reservabuscada.getUsuario().getId());
            usuarioDto.setNombre(reservabuscada.getUsuario().getNombre());
            usuarioDto.setApellido(reservabuscada.getUsuario().getApellido());
            usuarioDto.setEmail(reservabuscada.getUsuario().getEmail());
            reservaDto.setUsuario(usuarioDto);
        } else {
            System.out.println("La reserva no está registrada en la base de datos");
        }

        return reservaDto;
    }
}
