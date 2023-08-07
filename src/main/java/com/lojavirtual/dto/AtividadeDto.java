package com.lojavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeDto implements Serializable {
    private String code;
    private String text;
}
