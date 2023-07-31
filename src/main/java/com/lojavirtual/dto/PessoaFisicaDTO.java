package com.lojavirtual.dto;

import com.lojavirtual.model.ContaReceber;
import com.lojavirtual.model.Endereco;
import com.lojavirtual.model.NotaFiscalCompra;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class PessoaFisicaDTO {
    private String cpf;
    private Date dataNascimento;
    private String nome;
    private String email;
    private String telefone;
    private String tipoPessoa;
    private List<Endereco> enderecos;
    private List<ContaReceber> contaReceberList;
    private List<NotaFiscalCompra> notaFiscalCompras;
}
