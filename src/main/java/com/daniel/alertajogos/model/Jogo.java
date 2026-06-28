package com.daniel.alertajogos.model;

import com.daniel.alertajogos.observer.ObservadorUsuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    // Adicionei o ID aqui pois é uma boa prática para quando salvamos no Banco de Dados
    private Long id;
    private String titulo;
    private BigDecimal precoSugerido;
    private StatusJogo status;

    private List<ObservadorUsuario> observadores = new ArrayList<>();

    public Jogo(String titulo, BigDecimal precoSugerido, StatusJogo status) {
        this.titulo = titulo;
        this.precoSugerido = precoSugerido;
        this.status = status;
    }

    // ==========================================
    // MÉTODOS DO PADRÃO OBSERVER (OS GATILHOS)
    // ==========================================

    public void adicionarObservador(ObservadorUsuario observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorUsuario observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        // Passa por todos os usuários da lista e dispara o e-mail
        for (ObservadorUsuario observador : observadores) {
            observador.notificar(this);
        }
    }

    // ==========================================
    // GETTERS E SETTERS
    // ==========================================

    // O setStatus ganhou o "poder" de verificar a promoção e avisar a galera!
    public void setStatus(StatusJogo status) {
        this.status = status;

        if (this.status == StatusJogo.EM_PROMOCAO) {
            notificarObservadores();
        }
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public BigDecimal getPrecoSugerido() { return precoSugerido; }
    public void setPrecoSugerido(BigDecimal precoSugerido) { this.precoSugerido = precoSugerido; }

    public StatusJogo getStatus() { return status; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}