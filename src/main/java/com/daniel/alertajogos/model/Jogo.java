package com.daniel.alertajogos.model;

public class Jogo {
    private String titulo;
    private double precoSugerido;  // esta usando double mais futuramente ira usar o BigDecimal
    private String status; // depois iremos usar o Enum

    public Jogo(String titulo, double precoSugerido, String status) {
        this.titulo = titulo;
        this.precoSugerido = precoSugerido;
        this.status = status;
    }

    //Getters
    public String getTitulo() {return titulo;}
    public double getPrecoSugerido() {return precoSugerido;}
    public String getStatus() {return status;}
}


