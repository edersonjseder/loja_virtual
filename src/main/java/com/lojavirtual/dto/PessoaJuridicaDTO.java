package com.lojavirtual.dto;

import com.lojavirtual.model.ContaReceber;
import com.lojavirtual.model.Endereco;
import com.lojavirtual.model.NotaFiscalCompra;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PessoaJuridicaDTO {
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String nomeFantasia;
    private String razaoSocial;
    private String categoria;
    private String nome;
    private String email;
    private String telefone;
    private String tipoPessoa;
    private List<Endereco> enderecos;
    private List<ContaReceber> contaReceberList;
    private List<NotaFiscalCompra> notaFiscalCompras;
}
