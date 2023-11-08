package com.backend.PIBack.controller;


import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.mailSender.EmailService;
import com.backend.PIBack.service.impl.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    private final EmailService emailService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.emailService = emailService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDto> registarUsuario(@RequestBody Usuario usuario) throws MessagingException {
        emailService.sendWithImageFromURLRegister("pi.sinfonia23@gmail.com", usuario.getEmail(), "Confirmación de Registro Exitoso", usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(usuario));
    }

    //GET
    @GetMapping
    public List<UsuarioDto> listarUsuario() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioDto usuarioEncontrado = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@RequestBody Usuario usuario) {
        UsuarioDto usuarioActualizado = usuarioService.actualizarUsuario(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Se ha eliminado el usuario ");
    }

}
