package com.lojavirtual.service;

import com.lojavirtual.dto.CategoriaProdutoDto;
import com.lojavirtual.exception.CategoriaProdutoException;
import com.lojavirtual.exception.CategoriaProdutoNaoEncontradoException;
import com.lojavirtual.model.CategoriaProduto;
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

    public List<CategoriaProdutoDto> buscarListaCategoriasProdutos() {
        return categoriaUtils.toListaCategoriaProdutoDto(categoriaProdutoRepository.findAll());
    }

    public List<CategoriaProdutoDto> buscarCategoriasProdutosPorDescricao(String descricao) {
        return categoriaUtils.toListaCategoriaProdutoDto(categoriaProdutoRepository.buscarCategoriaProdutoPorDescricao(descricao));
    }

    public CategoriaProdutoDto buscarCategoriaProdutoPorNome(String nome) {
        return categoriaUtils
                .toCategoriaProdutoDto(categoriaProdutoRepository.findCategoriaProdutoByNome(nome)
                .orElseThrow(() -> new CategoriaProdutoNaoEncontradoException(nome)));
    }

    public CategoriaProdutoDto guardarCategoriaProduto(CategoriaProdutoDto categoriaProdutoDto) {
        if (categoriaProdutoDto.getEmpresa() == null) {
            throw new CategoriaProdutoException("A Empresa deve ser Informada.");
        }

        if (categoriaProdutoDto.getId() == null && categoriaProdutoRepository.existeCategoria(categoriaProdutoDto.getNome())) {
            throw new CategoriaProdutoException("Nao pode cadastrar categoria com o mesmo nome.");
        }

        return categoriaUtils
                .toCategoriaProdutoDto(categoriaProdutoRepository
                        .save(categoriaUtils.toCategoriaProduto(categoriaProdutoDto)));
    }

    public void removerCategoriaProduto(Long id) {
        categoriaProdutoRepository.deleteById(id);
    }
}
