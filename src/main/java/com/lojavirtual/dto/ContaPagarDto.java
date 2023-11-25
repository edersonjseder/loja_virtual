package com.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.lojavirtual.enums.StatusContaPagar;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaPagarDto {
    private Long id;

    @NotNull(message = "Valor total deve ser informado")
    private BigDecimal valorTotal;

    @NotBlank(message = "Data de vencimento deve ser informada")
    private String dataVencimento;

    private String dataPagamento;

    @NotBlank(message = "Descricao deve ser informada")
    private String descricao;

    @NotNull(message = "O status da conta deve ser informado")
    private StatusContaPagar status;

    @NotNull(message = "Empresa responsavel deve ser informada")
    private String empresaCnpj;

    @NotNull(message = "Fornecedor deve ser informado")
    private String fornecedorCnpj;

    @NotNull(message = "Campo Pessoa deve ser informado")
    private String pessoaCpf;

    private PessoaJuridicaDTO empresa;

    private PessoaJuridicaDTO fornecedor;

    private PessoaFisicaDTO pessoa;
}
