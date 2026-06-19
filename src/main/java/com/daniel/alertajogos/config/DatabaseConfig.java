package com.daniel.alertajogos.config;

import org.flywaydb.core.Flyway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/alerta_jogos?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //metodo para rodar as migration do Flyway
    public static void inicializarBanco() {
        System.out.println("Execuntando migrações do banco de dados via Flyway...");
        Flyway flyway = Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .load();
        flyway.migrate();
        System.out.println("Banco de dados atualizado com sucesso!");
    }

    //metodo para obeter uma conexão ativa com o banco
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
