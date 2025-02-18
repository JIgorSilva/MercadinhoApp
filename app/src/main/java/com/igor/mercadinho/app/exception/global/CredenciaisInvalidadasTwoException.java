package com.igor.mercadinho.app.exception.global;

public class CredenciaisInvalidadasTwoException extends RuntimeException {

    public CredenciaisInvalidadasTwoException(String menssagem) {
        super("Usuario ou senha invalidos.");
    }
}

