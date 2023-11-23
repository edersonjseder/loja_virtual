package com.lojavirtual.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long value) {
        super(generateMessage(value));
    }

    private static String generateMessage(Long value) {
        return "Produto nao encontrado com o ID: " + value;
    }
}
