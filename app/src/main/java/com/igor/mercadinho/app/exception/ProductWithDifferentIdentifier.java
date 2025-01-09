package com.igor.mercadinho.app.exception;

public class ProductWithDifferentIdentifier extends RuntimeException{
    public ProductWithDifferentIdentifier(String menssagem){
        super(menssagem);
    }
}
