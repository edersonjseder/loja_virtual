package com.lojavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegiaoDto {
    private Long id;
    private String sigla;
    private String nome;
}
