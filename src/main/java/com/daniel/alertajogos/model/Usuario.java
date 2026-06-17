package com.daniel.alertajogos.model;

public class Usuario {

    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
    }

    //Getters
    public String getNome() {return nome;}
    public String getEmail() {return email;}
}
