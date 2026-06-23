package com.daniel.alertajogos.dao;

import com.daniel.alertajogos.config.DatabaseConfig;
import com.daniel.alertajogos.model.Jogo;
import com.daniel.alertajogos.model.StatusJogo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {

    // INSERT - Salva um novo jogo utilizando BigDecimal e Enum
    public void salvar(Jogo jogo) {
        String sql = "INSERT INTO tb_jogo (titulo, preco_sugerido, status) VALUES (?, ?, ?)";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, jogo.getTitulo());
            // setBigDecimal garante precisão total para valores monetários
            pstmt.setBigDecimal(2, jogo.getPrecoSugerido());
            // Converte o valor do Enum para String para ser salvo no banco
            pstmt.setString(3, jogo.getStatus().name());

            pstmt.executeUpdate();
            System.out.println("Jogo salvo com sucesso: " + jogo.getTitulo());

        } catch (SQLException e) {
            System.err.println("Erro ao tentar salvar o jogo: " + e.getMessage());
        }
    }

    // SELECT - Procura e retorna todos os jogos cadastrados na tabela
    public List<Jogo> buscarTodos() {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM tb_jogo";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                BigDecimal preco = rs.getBigDecimal("preco_sugerido");
                // Converte a String de volta para a tipagem segura do Enum Java
                StatusJogo status = StatusJogo.valueOf(rs.getString("status"));

                Jogo jogo = new Jogo(titulo, preco, status);
                jogos.add(jogo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar os jogos: " + e.getMessage());
        }

        return jogos;
    }

    // SELECT com WHERE - Procura um jogo específico utilizando o título
    public Jogo buscarPorTitulo(String tituloBusca) {
        String sql = "SELECT * FROM tb_jogo WHERE titulo = ?";
        Jogo jogoEncontrado = null;

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, tituloBusca);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String titulo = rs.getString("titulo");
                    BigDecimal preco = rs.getBigDecimal("preco_sugerido");
                    StatusJogo status = StatusJogo.valueOf(rs.getString("status"));

                    jogoEncontrado = new Jogo(titulo, preco, status);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o jogo por título: " + e.getMessage());
        }

        return jogoEncontrado;
    }

    // UPDATE - Atualiza os dados de um jogo existente através do ID
    public void atualizar(Jogo jogo, int id) {
        String sql = "UPDATE tb_jogo SET titulo = ?, preco_sugerido = ?, status = ? WHERE id = ?";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, jogo.getTitulo());
            pstmt.setBigDecimal(2, jogo.getPrecoSugerido());
            pstmt.setString(3, jogo.getStatus().name());
            pstmt.setInt(4, id);

            int netLinhas = pstmt.executeUpdate();
            if (netLinhas > 0) {
                System.out.println("Jogo atualizado com sucesso no banco!");
            } else {
                System.out.println("Nenhum jogo encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar atualizar o jogo: " + e.getMessage());
        }
    }

    // DELETE - Apaga permanentemente um registro de jogo utilizando o ID
    public void deletar(int id) {
        String sql = "DELETE FROM tb_jogo WHERE id = ?";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int netLinhas = pstmt.executeUpdate();
            if (netLinhas > 0) {
                System.out.println("Jogo deletado com sucesso do banco!");
            } else {
                System.out.println("Nenhum jogo encontrado com o ID informado para exclusão.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar deletar o jogo: " + e.getMessage());
        }
    }
}