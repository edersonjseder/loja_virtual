package com.lojavirtual.exception;

public class CategoriaProdutoNaoEncontradoException extends RuntimeException {
    public CategoriaProdutoNaoEncontradoException(String value) {
        super(generateMessage(value));
    }

    private static String generateMessage(String value) {
        return "Categoria nao encontrada com o nome: " + value;
    }
}
