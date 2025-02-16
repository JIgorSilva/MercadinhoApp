package com.igor.mercadinho.app.exception.global;

public class SolicitacaoNaoAutorizadaException extends RuntimeException{
    public SolicitacaoNaoAutorizadaException(String menssagem){
        super(menssagem);
    }

}
