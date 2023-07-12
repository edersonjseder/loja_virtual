package com.lojavirtual.controller;

import com.lojavirtual.model.Acesso;
import com.lojavirtual.service.AcessoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class AcessoController {
    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    @GetMapping(value = "/carregarAcessos")
    public ResponseEntity<List<Acesso>> carregarAcessos() {
        var acessos = acessoService.carregarAcessos();
        return new ResponseEntity<>(acessos, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/salvarAcesso")
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
        var acessoSalvo =  acessoService.salvarAcesso(acesso);
        return new ResponseEntity<>(acessoSalvo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removerAcesso")
    public ResponseEntity<String> removerAcesso(@RequestBody Acesso acesso) {
        acessoService.removerAcesso(acesso.getId());
        return new ResponseEntity<>("Acesso Removido.", HttpStatus.OK);
    }
}
