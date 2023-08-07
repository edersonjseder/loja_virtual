package com.lojavirtual.controller;

import com.lojavirtual.dto.CategoriaProdutoDto;
import com.lojavirtual.service.CategoriaProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriaProdutoController {
    private final CategoriaProdutoService categoriaProdutoService;

    @ResponseBody
    @PostMapping(value = "/cadastrarCategoriaProduto")
    public ResponseEntity<CategoriaProdutoDto> cadastrarCategoriaProduto(@RequestBody CategoriaProdutoDto categoriaProdutoDto) {
        var categoriaCadastrada = categoriaProdutoService.cadastrarCategoriaProduto(categoriaProdutoDto);
        return new ResponseEntity<>(categoriaCadastrada, HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping(value = "/buscarCategoriasProdutos")
    public ResponseEntity<List<CategoriaProdutoDto>> buscarCategoriasProdutosLista() {
        var categorias = categoriaProdutoService.buscarListaCategoriasProdutos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
