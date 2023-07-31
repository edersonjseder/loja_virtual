package com.lojavirtual.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TipoEndereco {
    COBRANCA("Cobrança"),
    ENTREGA("Entrega"),
    COBRANCA_ENTREGA("Cobranca_Entrega");

    private final String descricao;

    TipoEndereco(String descricao) {
        this.descricao = descricao;
    }
}
