package com.igor.mercadinho.app.exception.global;

public class CredenciaisNaoAutorizadaException extends RuntimeException {
    public CredenciaisNaoAutorizadaException(String menssagem){
        super(menssagem);
    }

}
