package com.lojavirtual.security;

import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class WebConfigSecurity implements HttpSessionListener {
    public static final String[] ENDPOINTS_WHITELIST = {
            "/salvarAcesso",
            "/removerAcesso",
            "/",
            "/error"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll() .anyRequest().authenticated())
                .csrf().disable();
        return http.build();
    }
}
