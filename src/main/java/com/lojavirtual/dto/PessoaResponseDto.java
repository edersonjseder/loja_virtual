package com.lojavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponseDto {
    private String cpfCnpj;
    private String nome;
    private String email;
    private String username;
    private String dataAtualSenha;
    private String tipoPessoa;
    private String token;
}
