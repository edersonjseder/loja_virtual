package com.lojavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDto {
    private Long id;
    private String sigla;
    private String nome;
    private RegiaoDto regiao;
}
