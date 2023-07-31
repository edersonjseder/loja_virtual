package com.lojavirtual.enums;

public enum PessoaMensagemEnum {
    CPF_MENSAGEM ("Numeros invalidos do CPF: "),
    CNPJ_MENSAGEM ("Numeros invalidos do CNPJ: "),
    CPF_EXISTENTE_MENSAGEM ("Ja existe CPF cadastrado com o numero "),
    CNPJ_EXISTENTE_MENSAGEM ("Ja existe CNPJ cadastrado com o numero "),
    IE_EXISTENTE_MENSAGEM ("Ja existe IE cadastrado com o numero "),
    IM_EXISTENTE_MENSAGEM ("Ja existe IM cadastrado com o numero ");
    private final String mensagem;

    PessoaMensagemEnum(String mensagem) {
        this.mensagem = mensagem;
    }
}
