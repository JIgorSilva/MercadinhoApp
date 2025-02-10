package com.igor.mercadinho.app.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String menssagem){
        super("Email não vinculado a nenhuma conta.");
    }
}
