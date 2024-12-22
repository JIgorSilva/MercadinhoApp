package com.igor.mercadinho.app.exception;

public class ProdutoResouceNotFoundException extends RuntimeException{

    public ProdutoResouceNotFoundException(String menssagem){
        super(menssagem);
    }
}
