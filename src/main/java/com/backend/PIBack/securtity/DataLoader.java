package com.backend.PIBack.securtity;

import com.backend.PIBack.entity.Role;
import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Encriptacion de claves
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordAdmin = bCryptPasswordEncoder.encode("admin");
        String passwordUser = bCryptPasswordEncoder.encode("user");

        // Usuario ADMIN
        usuarioRepository.save(new Usuario("Administrador", "admin", "sinfoniaadm23@gmail.com", passwordAdmin, Role.ADMIN));

        // Usuario USER
        usuarioRepository.save(new Usuario("Usuario", "user", "usersinfonia@gmail.com", passwordUser, Role.USER));

    }

}
