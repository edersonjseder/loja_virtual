package com.lojavirtual.controller;

import com.lojavirtual.service.AcessoService;
import org.springframework.stereotype.Controller;

@Controller
public class AcessoController {
    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        this.acessoService = acessoService;
    }
}
