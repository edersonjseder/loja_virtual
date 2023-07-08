package com.lojavirtual.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TipoEndereco {
    COBRANCA("Cobrança"),
    ENTREGA("Entrega");

    private String descricao;

    TipoEndereco(String descricao) {
        this.descricao = descricao;
    }
}
