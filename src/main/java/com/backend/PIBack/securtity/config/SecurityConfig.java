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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
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
                                    .requestMatchers("api/auth/**").permitAll()
                                    .requestMatchers(HttpMethod.GET,"api/producto/**").permitAll()

                                    .requestMatchers("api/imagen/**").hasRole("ADMIN")

                                    .requestMatchers("api/admin/**").hasRole("ADMIN")

                                    .requestMatchers("api/usuario/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.POST,"api/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.PUT,"api/**").hasRole("ADMIN")

                                    .requestMatchers(HttpMethod.DELETE,"api/**").hasRole("ADMIN")

                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

