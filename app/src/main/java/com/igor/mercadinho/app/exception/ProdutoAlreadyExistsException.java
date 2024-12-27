package com.igor.mercadinho.app.exception;

public class ProdutoAlreadyExistsException extends RuntimeException {
    
    public ProdutoAlreadyExistsException(String menssagem) {
        super(menssagem);
    }
}
