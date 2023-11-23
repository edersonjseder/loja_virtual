package com.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lojavirtual.model.ContaReceber;
import com.lojavirtual.model.Endereco;
import com.lojavirtual.model.NotaFiscalCompra;
import com.lojavirtual.model.PessoaJuridica;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaJuridicaDTO {
    private Long id;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String nomeFantasia;
    private String razaoSocial;
    private String categoria;
    @NotEmpty(message = "Nome deve ser informado")
    private String nome;
    private String email;
    private String telefone;
    private String tipoPessoa;
    @NotNull(message = "Empresa deve ser informada")
    private PessoaJuridica empresa;
    private List<Endereco> enderecos;
    private List<ContaReceber> contaReceberList;
    private List<NotaFiscalCompra> notaFiscalCompras;
}
