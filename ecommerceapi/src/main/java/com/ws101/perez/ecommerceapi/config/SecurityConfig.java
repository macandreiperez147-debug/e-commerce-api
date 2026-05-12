package com.ws101.perez.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            // CSRF: only exclude register (LAB requirement)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/v1/auth/register")
            )

            // Return proper API response instead of redirects
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, authException) ->
                        res.sendError(401)
                )
            )

            .authorizeHttpRequests(auth -> auth

                // PUBLIC
                .requestMatchers("/csrf").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                .requestMatchers("/login", "/error").permitAll()

                // ADMIN ONLY (DELETE product)
                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**")
                    .hasRole("ADMIN")

                // EVERYTHING ELSE requires authentication
                .anyRequest().authenticated()
            )

            // Session login (JSESSIONID required)
            .formLogin(form -> form.permitAll())

            .logout(logout -> logout.permitAll())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }
}