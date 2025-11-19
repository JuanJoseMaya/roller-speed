package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // Páginas públicas
                .requestMatchers("/", "/mision", "/vision", "/valores", "/servicios", "/eventos").permitAll()
                .requestMatchers("/registro", "/guardar-aspirante", "/exito").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                // Páginas privadas (Dashboard, Listas, Eliminar)
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login") // Nuestra página personalizada
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Usuario Administrador
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345") // CONTRASEÑA
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
