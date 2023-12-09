package com.backend.PIBack.securtity.config;

import com.backend.PIBack.securtity.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(customizeRequests -> {
                            customizeRequests
                                    .requestMatchers(HttpMethod.POST, "/api/reservas/**").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/api/reservas/{id}").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/api/reservas").permitAll()
                                    .requestMatchers(HttpMethod.DELETE, "/api/reservas/**").permitAll()
                                    .requestMatchers("/api/auth/**").permitAll()

                                    .requestMatchers(HttpMethod.POST, "/api/usuarios/registrar").permitAll()

                                    .requestMatchers(HttpMethod.GET, "/api/usuarios/{id}").hasAnyRole("USER", "ADMIN")

                                    .requestMatchers("/api/categorias/**").permitAll()

                                    .requestMatchers("/api/caracteristicas/**").permitAll()

                                    .requestMatchers("/api/favoritos/**").hasAnyRole("USER", "ADMIN")

                                    .requestMatchers(HttpMethod.PUT, "/api/usuarios/actualizar").hasAnyRole("USER", "ADMIN")

                                    .requestMatchers(HttpMethod.DELETE, "/api/usuarios/{id}").hasAnyRole("USER", "ADMIN")

                                    .requestMatchers(HttpMethod.PUT, "/api/usuarios/actualizar").hasAnyRole("USER", "ADMIN")

                                    .requestMatchers(HttpMethod.GET, "api/producto/**").permitAll()

                                    .requestMatchers("/api/imagen/**").hasRole("ADMIN")

                                    .requestMatchers("/api/admin/**").hasRole("ADMIN")

                                    .requestMatchers("/api/usuarios/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "/api/upload").hasRole("ADMIN")


                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


