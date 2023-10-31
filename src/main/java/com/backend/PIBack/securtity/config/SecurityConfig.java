package com.backend.PIBack.securtity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizeRequests -> {
                            customizeRequests
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
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


