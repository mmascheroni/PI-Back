package com.backend.PIBack.controller;

import com.backend.PIBack.dto.LoginDto;
import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.mailSender.EmailService;
import com.backend.PIBack.repository.UsuarioRepository;
import com.backend.PIBack.securtity.jwt.JwtUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final EmailService emailService;

    private final UsuarioRepository usuarioRepository;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, EmailService emailService, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) throws MessagingException {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(login);


        String jwt = this.jwtUtil.createToken(loginDto.getEmail());


        loginDto.setJwt(jwt);

        LoginDto loginDtoSecure = LoginDto.fromLogin(loginDto);


        Optional<Usuario> usuario = usuarioRepository.findByEmail(loginDto.getEmail());

        loginDtoSecure.setUsuarioId(usuario.get().getId());
        loginDtoSecure.setNombre(usuario.get().getNombre());
        loginDtoSecure.setApellido(usuario.get().getApellido());
        loginDtoSecure.setRole(String.valueOf(usuario.get().getRole()));

//        String htmlBody = "<html><body>" +
//                "<p>Hola " + usuario.get().getNombre() + "!</p>" +
//                "<p>Te notificamos que el inicio de sesión se ha realizado de manera exitosa.</p>" +
//                "<p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.</p>" +
//                "<p>Saludos,<br>Sinfonía</p>" +
//                "<br>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/LogoSinfoniaEmail.png' alt='Logo'>" +
//                "<br>" +
//                "<b>Sinfonía - Equipo de Soporte Técnico" +
//                "<br>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/separador.png' alt='Separador'>" +
//                "<br>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/face-b.png' alt='ico-Facebook' style='margin-right: 15px;'>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/instagram-b.png' alt='ico-Instagram' style='margin-right: 15px;'>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/twitter-b.png' alt='ico-Twitter' style='margin-right: 15px;'>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/youtube-b.png' alt='ico-Youtube' style='margin-right: 15px;'>" +
//                "<img src='https://img-c9-g2-bucket.s3.amazonaws.com/Email/mapa-b.png' alt='ico-mapa'>" +
//                "</body></html>";

        emailService.sendWithImageFromURLLogin("pi.sinfonia23@gmail.com", loginDto.getEmail(), "Has iniciado sesión correctamente", usuario);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(loginDtoSecure);
    }

}
