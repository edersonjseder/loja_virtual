package com.lojavirtual.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StatusContaReceber {
    COBRANCA("Pagar"),
    VENCIDA("Vencida"),
    ABERTA("Aberta"),
    QUITADA("Quitada");

    private String descricao;

    private StatusContaReceber(String descricao) {
        this.descricao = descricao;
    }
}
