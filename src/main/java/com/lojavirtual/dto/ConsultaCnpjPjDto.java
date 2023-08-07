package com.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCnpjPjDto implements Serializable {
    private String abertura;
    private String situacao;
    private String tipo;
    private String nome;
    private String fantasia;
    private String porte;
    @JsonProperty("natureza_juridica")
    private String naturezaJuridica;
    private String logradouro;
    private String numero;
    private String complemento;
    private String municipio;
    private String bairro;
    private String uf;
    private String cep;
    private String email;
    private String telefone;
    @JsonProperty("data_situacao")
    private String dataSituacao;
    private String cnpj;
    @JsonProperty("ultima_atualizacao")
    private String ultimaAtualizacao;
    private String status;
    private String efr;
    @JsonProperty("motivo_situacao")
    private String motivoSituacao;
    @JsonProperty("situacao_especial")
    private String situacaoEspecial;
    @JsonProperty("data_situacao_especial")
    private String dataSituacaoEspecial;
    @JsonProperty("capital_social")
    private String capitalSocial;
    @JsonIgnore
    private ExtraDto extra;
    private BillingDto billing;
    @JsonProperty("atividade_principal")
    private List<AtividadeDto> atividadePrincipal;
    @JsonProperty("atividades_secundarias")
    private List<AtividadeDto> atividadesSecundarias;
    private List<QsaDto> qsa;
}
