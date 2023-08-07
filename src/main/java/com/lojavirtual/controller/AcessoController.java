package com.lojavirtual.controller;

import com.lojavirtual.model.Acesso;
import com.lojavirtual.service.AcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcessoController {
    private final AcessoService acessoService;

    @GetMapping(value = "/carregarAcessos")
    public ResponseEntity<List<Acesso>> carregarAcessos() {
        var acessos = acessoService.carregarAcessos();
        return new ResponseEntity<>(acessos, HttpStatus.OK);
    }

    @GetMapping(value = "/carregarAcessoPorId/{id}")
    public ResponseEntity<Acesso> carregarAcessoPorId(@PathVariable("id") Long id) {
        var acesso = acessoService.carregarAcessoPorId(id);
        return new ResponseEntity<>(acesso, HttpStatus.OK);
    }

    @GetMapping(value = "/carregarAcessosPorDescricao/{descricao}")
    public ResponseEntity<List<Acesso>> carregarAcessosPorDescricao(@PathVariable("descricao") String descricao) {
        var acessos = acessoService.carregarAcessosPorDescricao(descricao);
        return new ResponseEntity<>(acessos, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/salvarAcesso")
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
        var acessoSalvo =  acessoService.salvarAcesso(acesso);
        return new ResponseEntity<>(acessoSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/removerAcesso")
    public ResponseEntity<String> removerAcesso(@RequestBody Acesso acesso) {
        acessoService.removerAcesso(acesso.getId());
        return new ResponseEntity<>("Acesso Removido.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/removerAcessoPorId/{id}")
    public ResponseEntity<String> removerAcesso(@PathVariable("id") Long id) {
        acessoService.removerAcesso(id);
        return new ResponseEntity<>("Acesso Removido.", HttpStatus.OK);
    }
}
