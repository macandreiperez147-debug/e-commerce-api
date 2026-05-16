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

    /**
     * Password encoder using BCrypt hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Main Spring Security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            // CSRF protection
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/v1/auth/register", "/api/v1/products/**")
            )

            // Authorization rules
            .authorizeHttpRequests(auth -> auth

                // Public endpoints
                .requestMatchers("/login", "/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()

                // Admin-only endpoints
                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**")
                    .hasRole("ADMIN")

                // All other endpoints require authentication
                .anyRequest().authenticated()
            )

            // Return 401 for API requests only
            .exceptionHandling(ex -> ex
                .defaultAuthenticationEntryPointFor(
                    (req, res, authException) ->
                        res.sendError(401, "Unauthorized"),
                    request -> request.getRequestURI().startsWith("/api/")
                )
            )

            // Default Spring Security login page
            .formLogin(form -> form.permitAll())

            // Logout support
            .logout(logout -> logout.permitAll())

            // Session-based authentication
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }
}