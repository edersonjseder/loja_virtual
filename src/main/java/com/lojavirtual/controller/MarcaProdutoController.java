package com.lojavirtual.controller;

import com.lojavirtual.dto.MarcaProdutoDto;
import com.lojavirtual.service.MarcaProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MarcaProdutoController {
    private final MarcaProdutoService marcaProdutoService;

    @ResponseBody
    @GetMapping(value = "/buscarMarcasProdutos")
    public ResponseEntity<List<MarcaProdutoDto>> buscarMarcaProdutoLista() {
        var marcas = marcaProdutoService.buscarListaMarcaProduto();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarMarcaProdutoPorDescricao/{descricao}")
    public ResponseEntity<List<MarcaProdutoDto>> buscarMarcaProdutoPorDescricao(@PathVariable("descricao") String descricao) {
        var marcas = marcaProdutoService.buscarMarcaProdutoPorDescricao(descricao);
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/cadastrarMarcaProduto")
    public ResponseEntity<MarcaProdutoDto> cadastrarMarcaProduto(@RequestBody MarcaProdutoDto marcaProdutoDto) {
        var marcaCadastrada = marcaProdutoService.guardarMarcaProduto(marcaProdutoDto);
        return new ResponseEntity<>(marcaCadastrada, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping(value = "/atualizarMarcaProduto")
    public ResponseEntity<MarcaProdutoDto> atualizarMarcaProduto(@RequestBody MarcaProdutoDto marcaProdutoDto) {
        var marcaAtualizada = marcaProdutoService.guardarMarcaProduto(marcaProdutoDto);
        return new ResponseEntity<>(marcaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removerMarcaProduto/{id}")
    public ResponseEntity<String> removerMarcaProduto(@PathVariable("id") Long id) {
        marcaProdutoService.removerMarcaProduto(id);
        return new ResponseEntity<>("Marca Removida.", HttpStatus.OK);
    }
}
