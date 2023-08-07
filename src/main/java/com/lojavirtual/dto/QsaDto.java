package com.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QsaDto implements Serializable {
    private String nome;
    private String qual;
    @JsonProperty("pais_origem")
    private String paisOrigem;
    @JsonProperty("nome_rep_legal")
    private String nomeRepLegal;
    @JsonProperty("qual_rep_legal")
    private String qualRepLegal;
}
