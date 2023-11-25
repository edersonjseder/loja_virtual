package com.lojavirtual.exception;

public class ContaPagarNaoEncontradaException extends RuntimeException {
    public ContaPagarNaoEncontradaException(Long value) {
        super(generateMessage(value));
    }

    private static String generateMessage(Long value) {
        return "Nao encontrou conta a pagar com o ID: " + value;
    }
}
