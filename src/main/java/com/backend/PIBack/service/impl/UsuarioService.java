package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.repository.UsuarioRepository;
import com.backend.PIBack.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;

    private final ObjectMapper objectMapper;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UsuarioDto registrarUsuario(Usuario usuario) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String passwordUsuario = bCryptPasswordEncoder.encode(usuario.getPassword());

        usuario.setPassword(passwordUsuario);

        UsuarioDto usuarioDto = objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioDto.class);

        LOGGER.info("Se guardó el usuario: {}", usuarioDto);

        return usuarioDto;
    }

    @Override
    public UsuarioDto buscarUsuarioPorId(Long id) {
        Usuario usuarioBuscado = usuarioRepository.findById(id).orElse(null);
        UsuarioDto usuarioDto = null;

        if (usuarioBuscado != null) {
            usuarioDto = objectMapper.convertValue(usuarioBuscado, UsuarioDto.class);
            LOGGER.info("Usuario encontrado: {}", usuarioDto);
        } else {
            LOGGER.error("El Usuario buscado con id {}, no se encuentra registrado en la base de datos", id);
        }
        return usuarioDto;
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDto> usuarioDtos = usuarioRepository
                .findAll()
                .stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDto.class))
                .toList();
        LOGGER.info("Listando todos los usuarios: {}", usuarioDtos);
        return usuarioDtos;
    }


    @Override
    public UsuarioDto actualizarUsuario(Usuario usuario) {
        Usuario usuarioAActualizar = usuarioRepository.findById(usuario.getId()).orElse(null);
        UsuarioDto usuarioActualizadoDto = null;
        if (usuarioAActualizar != null) {
            usuarioAActualizar = usuario;
            usuarioActualizadoDto = registrarUsuario(usuarioAActualizar);
            LOGGER.warn("El usuario con ID {} ha sido actualizado: {}", usuario.getId(), usuarioActualizadoDto);
        } else {
            LOGGER.warn("No es posible actualizar el usuario porque no está registrado en la base de datos");
        }
        return usuarioActualizadoDto;
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (buscarUsuarioPorId(id) != null) {
            usuarioRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado al usuario con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el usuario con id " + id);
        }
    }
}
