package com.lojavirtual.enums;

import lombok.ToString;

@ToString
public enum TipoPessoa {
    FISICA("Física"),
    JURIDICA("Jurídica"),
    JURIDICA_FORNECEDOR("Jurídica e Fornecedor");

    private String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }
}
