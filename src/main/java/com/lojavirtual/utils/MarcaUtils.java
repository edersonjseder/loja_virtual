package com.lojavirtual.utils;

import com.lojavirtual.dto.MarcaProdutoDto;
import com.lojavirtual.model.MarcaProduto;
import com.lojavirtual.model.PessoaJuridica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MarcaUtils {
    private final PessoaUtils pessoaUtils;
    public MarcaProduto toMarcaProduto(MarcaProdutoDto marcaProdutoDto) {
        return MarcaProduto.builder()
                .id(marcaProdutoDto.getId())
                .nome(marcaProdutoDto.getNome())
                .descricao(marcaProdutoDto.getDescricao())
                .empresa(pessoaUtils.toPessoaJuridica(marcaProdutoDto.getEmpresa()))
                .build();
    }

    public MarcaProdutoDto toMarcaProdutoDto(MarcaProduto marcaProduto) {
        return MarcaProdutoDto.builder()
                .id(marcaProduto.getId())
                .nome(marcaProduto.getNome())
                .descricao(marcaProduto.getDescricao())
                .build();
    }

    public List<MarcaProdutoDto> toListaMarcaProdutoDto(List<MarcaProduto> marcaProdutos) {
        return marcaProdutos.stream().map(marcaProduto -> MarcaProdutoDto.builder()
                .id(marcaProduto.getId())
                .nome(marcaProduto.getNome())
                .descricao(marcaProduto.getDescricao())
                .build()).collect(Collectors.toList());
    }
}
