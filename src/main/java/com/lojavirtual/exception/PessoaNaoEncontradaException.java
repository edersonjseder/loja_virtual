package com.lojavirtual.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String value) {
        super(value);
    }
}
