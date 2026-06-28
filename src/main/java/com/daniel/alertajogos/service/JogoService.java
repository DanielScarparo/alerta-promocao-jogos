package com.daniel.alertajogos.service;
import com.daniel.alertajogos.exception.ValidacaoJogoException;
import com.daniel.alertajogos.model.Jogo;
import com.daniel.alertajogos.model.StatusJogo;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class JogoService {

    public List<Jogo> filtrarApenasPromocoes(List<Jogo> todosOsJogos) {
        return todosOsJogos.stream()
                .filter(jogo -> jogo.getStatus() == StatusJogo.EM_PROMOCAO)
                .collect(Collectors.toList());
    }

    public List<String> obterApenasNomesDosJogos(List<Jogo> todosOsJogos) {
        return todosOsJogos.stream()
                .map(jogo -> jogo.getTitulo())
                .collect(Collectors.toList());
    }

    public List<Jogo> ordernarJogosPorPrecoCrescente(List<Jogo> todosOsJogos) {
        return todosOsJogos.stream()
                .sorted(Comparator.comparing(jogo -> jogo.getPrecoSugerido()))
                .collect(Collectors.toList());
    }

    public Optional<Jogo> encontrarJogoMaisBarato(List<Jogo> todosOsJogos) {
        return todosOsJogos.stream()
                .min(Comparator.comparing(jogo -> jogo.getPrecoSugerido()));
    }

    public void validarNovoJogo (Jogo jogo) {
        //verifica se o jogo é nulo
        if (jogo ==null) {
            throw new ValidacaoJogoException("o jogo não pode ser nulo.");
        }

        //verifica se o titulo esta em branco o vazio
        if (jogo.getTitulo() == null || jogo.getTitulo().trim().isEmpty()) {
            throw new ValidacaoJogoException("o Titulo do jogo e obrigatorio e não pode ficar em branco");
        }

        //verifica se o preço e negativo
        if (jogo.getPrecoSugerido() != null && jogo.getPrecoSugerido().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidacaoJogoException("O preço do jogo não pode ser negativo. Valor informado: R$" + jogo.getPrecoSugerido());
        }

        System.out.println("Validação concluida: Os dados do jogo: '" + jogo.getTitulo() + "' estão corretos!");
    }

}
