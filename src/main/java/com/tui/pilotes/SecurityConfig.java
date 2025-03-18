package com.tui.pilotes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
            .authorizeExchange(exchanges -> exchanges

                .pathMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",       // Swagger UI resources
                        "/v3/api-docs/**",      // OpenAPI JSON docs
                        "/webjars/**",         // WebJars for Swagger UI
                        "/swagger-resources/**", // Swagger resources
                        "/v1/order/**",
                        "/v1/client/**").permitAll()

                .pathMatchers("/v1/search/**").authenticated()

                .pathMatchers(
                        "/v1/search/**").hasAnyRole("USER")

            )
            .httpBasic(Customizer.withDefaults())
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .build();

    }








}
