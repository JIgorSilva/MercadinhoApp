package com.igor.mercadinho.app.dtos.usuario;


public class LoginUsuarioDto {
    private String email;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginUsuarioDto() {
    }

    public LoginUsuarioDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

}
