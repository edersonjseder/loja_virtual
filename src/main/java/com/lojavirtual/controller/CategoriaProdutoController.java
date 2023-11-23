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
    @GetMapping(value = "/buscarCategoriasProdutos")
    public ResponseEntity<List<CategoriaProdutoDto>> buscarCategoriasProdutosLista() {
        var categorias = categoriaProdutoService.buscarListaCategoriasProdutos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarCategoriasProdutosPorDescricao/{descricao}")
    public ResponseEntity<List<CategoriaProdutoDto>> buscarCategoriasProdutosPorDescricao(@PathVariable("descricao") String descricao) {
        var categorias = categoriaProdutoService.buscarCategoriasProdutosPorDescricao(descricao);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/cadastrarCategoriaProduto")
    public ResponseEntity<CategoriaProdutoDto> cadastrarCategoriaProduto(@RequestBody CategoriaProdutoDto categoriaProdutoDto) {
        var categoriaCadastrada = categoriaProdutoService.guardarCategoriaProduto(categoriaProdutoDto);
        return new ResponseEntity<>(categoriaCadastrada, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping(value = "/atualizarCategoriaProduto")
    public ResponseEntity<CategoriaProdutoDto> atualizarCategoriaProduto(@RequestBody CategoriaProdutoDto categoriaProdutoDto) {
        var categoriaAtualizada = categoriaProdutoService.guardarCategoriaProduto(categoriaProdutoDto);
        return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removerCategoriaProduto/{id}")
    public ResponseEntity<String> removerCategoriaProduto(@PathVariable("id") Long id) {
        categoriaProdutoService.removerCategoriaProduto(id);
        return new ResponseEntity<>("Categoria Removida.", HttpStatus.OK);
    }
}
