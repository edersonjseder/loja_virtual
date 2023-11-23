package com.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarcaProdutoDto {
    private Long id;
    @NotBlank(message = "Nome da marca deve ser informado")
    private String nome;
    private String descricao;
    private PessoaJuridicaDTO empresa;
}
