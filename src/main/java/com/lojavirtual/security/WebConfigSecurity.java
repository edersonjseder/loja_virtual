package com.lojavirtual.security;

import com.lojavirtual.filters.JwtAuthenticationFilter;
import com.lojavirtual.service.UserDetailServiceImpl;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebConfigSecurity implements HttpSessionListener {
    private final UserDetailServiceImpl userDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    public static final String[] ENDPOINTS_WHITELIST = {
            "/signup",
            "/signin"
    };
    public static final String[] ALLOWED_ORIGINS = {
            "http://localhost:4200",
            "http://localhost:5000"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(request ->
                         request.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .exceptionHandling((exception) ->
                        exception.authenticationEntryPoint(restAuthenticationEntryPoint)
                                .accessDeniedHandler(restAccessDeniedHandler)
                );

        return http.build();
    }
    /* Metodo para criptografar e descriptografar a senha */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /* Metodo obtem o administrador de autenticacao do framework */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    /* Authentication Provider que e o servico de buscar usuario por suas credenciais no banco de dados
       para ser usado pelo framework quando receber requisicoes REST para autenticacao */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
