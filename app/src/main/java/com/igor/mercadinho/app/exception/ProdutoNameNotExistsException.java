package com.igor.mercadinho.app.exception;

public class ProdutoNameNotExistsException extends RuntimeException {
    public ProdutoNameNotExistsException(String menssagem){
        super(menssagem);
    }
}