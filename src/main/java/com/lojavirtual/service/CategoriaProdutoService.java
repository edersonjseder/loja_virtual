package com.lojavirtual.service;

import com.lojavirtual.dto.CategoriaProdutoDto;
import com.lojavirtual.repository.CategoriaProdutoRepository;
import com.lojavirtual.utils.CategoriaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaProdutoService {
    private final CategoriaUtils categoriaUtils;
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public CategoriaProdutoDto cadastrarCategoriaProduto(CategoriaProdutoDto categoriaProdutoDto) {
        return categoriaUtils
                .toCategoriaProdutoDto(categoriaProdutoRepository
                        .save(categoriaUtils.toCategoriaProduto(categoriaProdutoDto)));
    }

    public List<CategoriaProdutoDto> buscarListaCategoriasProdutos() {
        return categoriaUtils.toListaCategoriaProdutoDto(categoriaProdutoRepository.findAll());
    }
}
