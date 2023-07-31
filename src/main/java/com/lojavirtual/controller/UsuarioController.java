package com.lojavirtual.controller;

import com.lojavirtual.security.request.SignInRequest;
import com.lojavirtual.security.request.SignUpRequest;
import com.lojavirtual.response.TokenResponse;
import com.lojavirtual.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(usuarioService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(usuarioService.signIn(request));
    }
}
