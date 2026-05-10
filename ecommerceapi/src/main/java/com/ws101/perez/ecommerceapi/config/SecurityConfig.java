package com.ws101.perez.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            // CSRF ENABLED (LAB REQUIREMENT)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/v1/auth/register")
            )

            // AUTH RULES
            .authorizeHttpRequests(auth -> auth

                // PUBLIC ENDPOINTS
                .requestMatchers(HttpMethod.GET,
                        "/api/v1/products/**").permitAll()

                .requestMatchers(HttpMethod.POST,
                        "/api/v1/auth/register").permitAll()

                // LOGIN ACCESS
                .requestMatchers("/login", "/error").permitAll()

                // PROTECTED ENDPOINTS
                .requestMatchers(HttpMethod.POST,
                        "/api/v1/orders/**").authenticated()

                .requestMatchers(HttpMethod.DELETE,
                        "/api/v1/products/**").authenticated()

                .anyRequest().authenticated()
            )

            // FORM LOGIN (LAB REQUIRED)
            .formLogin(form -> form
                .defaultSuccessUrl("/api/v1/products", true)
                .permitAll()
            )

            // LOGOUT
            .logout(Customizer.withDefaults())

            // SESSION BASED AUTH
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }
}