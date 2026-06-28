package com.daniel.alertajogos.model;
import com.daniel.alertajogos.observer.ObservadorUsuario;

public class Usuario implements ObservadorUsuario {

    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
    }

    @Override
    public void notificar(Jogo jogo) {
        System.out.println("📬 [E-MAIL ENVIADO] Para: " + email + " (" + nome + ")");
        System.out.println("   Olá " + nome + ", um jogo da sua lista de favoritos mudou de status!");
        System.out.println("   🔥 " + jogo.getTitulo() + " está EM PROMOÇÃO por apenas R$ " + jogo.getPrecoSugerido() + "!\n");
    }


    //Getters
    public String getNome() {return nome;}
    public String getEmail() {return email;}
}
