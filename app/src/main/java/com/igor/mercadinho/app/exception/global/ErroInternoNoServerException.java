package com.igor.mercadinho.app.exception.global;

public class ErroInternoNoServerException extends RuntimeException {
    public ErroInternoNoServerException(String menssagem){
        super(menssagem);
    }

}
