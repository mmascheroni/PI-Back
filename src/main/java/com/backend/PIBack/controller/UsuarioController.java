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
        String htmlBody = "<html><body>" +
                "<p>Hola " + usuario.getNombre() + "!</p>" +
                "<p>Es un placer darte la bienvenida a Sinfonía, nos complace confirmar que tu registro ha sido exitoso y que ahora eres parte de nuestra comunidad.</p>" +
                "<p>A continuación, te proporcionamos los detalles de tu registro: <br>" +
                "\n" +
                "<b>• Nombre:</b> " + usuario.getNombre() + " " + usuario.getApellido() + "<br>" +
                "<b>• Email:</b> " + usuario.getEmail() +"</p>" +
                "<p>Para confirmar que la información ingresada es correcta, por favor, haz clic en el siguiente enlace para iniciar sesión en tu cuenta recién creada:\n" +
                "\n" +
                "http://localhost:5173/login</p>" +
                "<p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.</p>" +
                "<p>Saludos,<br>Sinfonía</p>" +
                "<br>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/LogoSinfoniaEmail.png' alt='Logo'>" +
                "<br>" +
                "<b>Sinfonía - Equipo de Soporte Técnico" +
                "<br>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/separador.png' alt='Separador'>" +
                "<br>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/face-b.png' alt='ico-Facebook' style='margin-right: 15px;'>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/instagram-b.png' alt='ico-Instagram' style='margin-right: 15px;'>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/twitter-b.png' alt='ico-Twitter' style='margin-right: 15px;'>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/youtube-b.png' alt='ico-Youtube' style='margin-right: 15px;'>" +
                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/mapa-b.png' alt='ico-mapa'>" +
                "</body></html>";

        emailService.sendWithImageFromURL("pi.sinfonia23@gmail.com", usuario.getEmail(), "Confirmación de Registro Exitoso", htmlBody);

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
