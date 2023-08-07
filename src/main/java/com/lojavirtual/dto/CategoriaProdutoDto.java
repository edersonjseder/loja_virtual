package com.lojavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private PessoaJuridicaDTO empresa;
}
