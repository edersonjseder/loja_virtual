package com.lojavirtual.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(long value) {
        super(generateMessage(value));
    }

    public UsuarioNaoEncontradoException(String value) {
        super(generateMessage(value));
    }

    private static String generateMessage(Long value) {
        return "Usuario nao encontrado com parametro: " + value;
    }

    private static String generateMessage(String value) {
        return "Usuario nao encontrado com o valor: " + value;
    }
}
