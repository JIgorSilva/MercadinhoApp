package com.igor.mercadinho.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false,length = 100)
    private String senha;

    public Usuario(){

    }
    public Usuario(Long id, String nome, String sobrenome, String email, String senha){
        this.id=id;
        this.sobrenome=sobrenome;
        this.nome=nome;
        this.email=email;
        this.senha=senha;
    }

}
