package com.backend.PIBack.securtity;

import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email).get();

        String role = String.valueOf(usuario.getRole());

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }


        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(role)
                .build();
    }
}
