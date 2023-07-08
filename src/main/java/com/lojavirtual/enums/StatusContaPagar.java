package com.lojavirtual.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StatusContaPagar {
    COBRANCA("Pagar"),
    VENCIDA("Vencida"),
    ABERTA("Aberta"),
    QUITADA("Quitada"),
    ALUGUEL("Quitada"),
    FUNCIONARIO("Funcionário"),
    NEGOCIADA("Negociada");

    private String descricao;

    private StatusContaPagar(String descricao) {
        this.descricao = descricao;
    }
}
