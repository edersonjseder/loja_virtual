package com.lojavirtual.exception;

public class MarcaProdutoNaoEncontradoException extends RuntimeException {
    public MarcaProdutoNaoEncontradoException(String value) {
        super(generateMessage(value));
    }

    private static String generateMessage(String value) {
        return "Marca nao encontrada com o nome: " + value;
    }
}
