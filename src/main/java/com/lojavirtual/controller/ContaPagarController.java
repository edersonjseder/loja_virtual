package com.lojavirtual.controller;

import com.lojavirtual.dto.ContaPagarDto;
import com.lojavirtual.dto.MarcaProdutoDto;
import com.lojavirtual.model.ContaPagar;
import com.lojavirtual.service.ContaPagarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContaPagarController {
    private final ContaPagarService contaPagarService;

    @GetMapping(value = "/buscarContasPagar")
    public ResponseEntity<List<ContaPagarDto>> buscarContaPagarLista() {
        var marcas = contaPagarService.buscarContaPagarLista();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarContaPagarPorDescricao/{descricao}")
    public ResponseEntity<List<ContaPagar>> buscarContaPagarPorDescricao(@PathVariable("descricao") String descricao) {
        var contasPagar = contaPagarService.buscarContaPorDescricao(descricao);
        return new ResponseEntity<>(contasPagar, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarContaPagarPorId/{id}")
    public ResponseEntity<ContaPagar> buscarContaPagarPorId(@PathVariable("id") Long id) {
        var contaPagar = contaPagarService.buscarContaPorId(id);
        return new ResponseEntity<>(contaPagar, HttpStatus.OK);
    }

    @PostMapping(value = "/cadastrarContaPagar")
    public ResponseEntity<ContaPagarDto> cadastrarContaPagar(@RequestBody @Valid ContaPagarDto contaPagarDto) {
        var contaPagarRegistrada = contaPagarService.salvarContaPagar(contaPagarDto);
        return new ResponseEntity<>(contaPagarRegistrada, HttpStatus.CREATED);
    }

    @PutMapping(value = "/atualizarContaPagar")
    public ResponseEntity<ContaPagarDto> atualizarContaPagar(@RequestBody @Valid ContaPagarDto contaPagarDto) {
        var contaPagarAtualizada = contaPagarService.salvarContaPagar(contaPagarDto);
        return new ResponseEntity<>(contaPagarAtualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removerContaPagar/{id}")
    public ResponseEntity<String> removerContaPagar(@PathVariable("id") Long id) {
        contaPagarService.removerContaPagar(id);
        return new ResponseEntity<>("Conta Removida.", HttpStatus.OK);
    }
}
