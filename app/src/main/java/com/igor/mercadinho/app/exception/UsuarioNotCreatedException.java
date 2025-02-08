package com.igor.mercadinho.app.exception;

public class UsuarioNotCreatedException extends RuntimeException{
    public UsuarioNotCreatedException(String menssagem){
        super("Impossivel Criar Usuario");
    }
}
