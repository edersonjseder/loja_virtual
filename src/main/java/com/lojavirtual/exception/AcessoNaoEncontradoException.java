package com.lojavirtual.exception;

public class AcessoNaoEncontradoException extends RuntimeException {
    public AcessoNaoEncontradoException(long value) {
        super(generateMessage(value));
    }

    public AcessoNaoEncontradoException(String value) {
        super(generateMessage(value));
    }

    private static String generateMessage(Long value) {
        return "Acesso nao encontrado com parametro: " + value;
    }

    private static String generateMessage(String value) {
        return "Acesso nao encontrado com o valor: " + value;
    }
}
