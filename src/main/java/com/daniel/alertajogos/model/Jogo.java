package com.daniel.alertajogos.model;

import java.math.BigDecimal;

public class Jogo {
    private String titulo;
    private BigDecimal precoSugerido;  // esta usando double mais futuramente ira usar o BigDecimal
    private StatusJogo status; // depois iremos usar o Enum

    public Jogo(String titulo, BigDecimal precoSugerido, StatusJogo status) {
        this.titulo = titulo;
        this.precoSugerido = precoSugerido;
        this.status = status;
    }

    //Getters
    public String getTitulo() {return titulo;}
    public BigDecimal getPrecoSugerido() {return precoSugerido;}
    public StatusJogo getStatus() {return status;}
}


