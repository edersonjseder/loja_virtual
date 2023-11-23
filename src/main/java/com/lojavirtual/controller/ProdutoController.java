package com.lojavirtual.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lojavirtual.dto.ProdutoDto;
import com.lojavirtual.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @ResponseBody
    @GetMapping(value = "/buscarProdutos")
    public ResponseEntity<List<ProdutoDto>> buscarCategoriasProdutosLista() {
        var produtos = produtoService.buscarListaProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarProdutosPorDescricao/{descricao}")
    public ResponseEntity<List<ProdutoDto>> buscarProdutosPorDescricao(@PathVariable("descricao") String descricao) {
        var produtos = produtoService.buscarProdutosPorDescricao(descricao);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

//    @ResponseBody
//    @GetMapping(value = "/buscarProdutosPorMarca/{marca}")
//    public ResponseEntity<List<ProdutoDto>> buscarProdutosPorMarca(@PathVariable("marca") String marca) {
//        var produtos = produtoService.buscarProdutosPorMarca(marca);
//        return new ResponseEntity<>(produtos, HttpStatus.OK);
//    }

    @ResponseBody
    @GetMapping(value = "/buscarProdutoPorModelo/{modelo}")
    public ResponseEntity<ProdutoDto> buscarProdutoPorModelo(@PathVariable("modelo") String modelo) {
        var produto = produtoService.buscarProdutoPorModelo(modelo);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/cadastrarProduto")
    public ResponseEntity<ProdutoDto> cadastrarProduto(@RequestBody @Validated(ProdutoDto.ProdutoView.ProdutoPost.class) @JsonView(ProdutoDto.ProdutoView.ProdutoPost.class) ProdutoDto produtoDto) {
        var produtoCadastrado = produtoService.salvarProduto(produtoDto);
        return new ResponseEntity<>(produtoCadastrado, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping(value = "/atualizarProduto")
    public ResponseEntity<ProdutoDto> atualizarProduto(@RequestBody @Validated(ProdutoDto.ProdutoView.ProdutoPut.class) @JsonView(ProdutoDto.ProdutoView.ProdutoPut.class) ProdutoDto produtoDto) {
        var produtoAtualizado = produtoService.salvarProduto(produtoDto);
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/removerProduto/{id}")
    public ResponseEntity<String> removerProduto(@PathVariable("id") Long id) {
        produtoService.removerProduto(id);
        return new ResponseEntity<>("Produto Removido.", HttpStatus.OK);
    }
}
