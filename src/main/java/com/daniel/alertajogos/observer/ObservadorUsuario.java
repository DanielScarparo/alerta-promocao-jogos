package com.daniel.alertajogos.observer;

import com.daniel.alertajogos.model.Jogo;

public interface ObservadorUsuario {
    void notificar(Jogo jogo);
}
