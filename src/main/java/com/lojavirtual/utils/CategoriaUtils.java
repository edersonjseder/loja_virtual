package com.lojavirtual.utils;

import com.lojavirtual.dto.CategoriaProdutoDto;
import com.lojavirtual.model.CategoriaProduto;
import com.lojavirtual.model.PessoaJuridica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoriaUtils {
    private final PessoaUtils pessoaUtils;
    public CategoriaProduto toCategoriaProduto(CategoriaProdutoDto categoriaProdutoDto) {
        return CategoriaProduto.builder()
                .id(categoriaProdutoDto.getId())
                .nome(categoriaProdutoDto.getNome())
                .descricao(categoriaProdutoDto.getDescricao())
                .empresa(pessoaUtils.toPessoaJuridica(categoriaProdutoDto.getEmpresa()))
                .build();
    }

    public CategoriaProdutoDto toCategoriaProdutoDto(CategoriaProduto categoriaProduto) {
        return CategoriaProdutoDto.builder()
                .id(categoriaProduto.getId())
                .nome(categoriaProduto.getNome())
                .descricao(categoriaProduto.getDescricao())
                .empresa(pessoaUtils.toPessoaJuridicaDto((PessoaJuridica) categoriaProduto.getEmpresa()))
                .build();
    }

    public List<CategoriaProdutoDto> toListaCategoriaProdutoDto(List<CategoriaProduto> categoriasProdutos) {
        return categoriasProdutos.stream().map(categoriaProduto -> CategoriaProdutoDto.builder()
                .id(categoriaProduto.getId())
                .nome(categoriaProduto.getNome())
                .descricao(categoriaProduto.getDescricao())
                .empresa(pessoaUtils.toPessoaJuridicaDto((PessoaJuridica) categoriaProduto.getEmpresa()))
                .build()).collect(Collectors.toList());
    }
}
