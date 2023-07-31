package com.lojavirtual.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PessoaResponseDto {
    private String cpfCnpj;
    private String nome;
    private String email;
    private String username;
    private String dataAtualSenha;
    private String tipoPessoa;
}
