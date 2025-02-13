    package com.igor.mercadinho.app.model;

    import jakarta.persistence.*;

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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public Usuario(){

        }
        public Usuario(Long id, String nome, String sobrenome, String email, String senha){
            this.id=id;
            this.sobrenome=sobrenome;
            this.nome=nome;
            this.email=email;
            this.senha=senha;
        }
        public Usuario(String email,String senha){
            this.email=email;
            this.senha=senha;
        }

    }
