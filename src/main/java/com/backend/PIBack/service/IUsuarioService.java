package com.backend.PIBack.service;

import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    UsuarioDto registrarUsuario(Usuario usuario);

    UsuarioDto buscarUsuarioPorId(Long id);

    List<UsuarioDto> listarUsuarios();

    UsuarioDto actualizarUsuario(Usuario usuario);

    void eliminarUsuario(Long id);
}
