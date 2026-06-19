import com.daniel.alertajogos.config.DatabaseConfig;
import java.sql.Connection;

public class Main {
    static void main(String[] args) {
        System.out.println("Iniciando Sistema de Alerta de Jogos");

        try {
            // inicializa o banco de dados e cria as tabelas se não existirem
            DatabaseConfig.inicializarBanco();
            // testa a conexão direta do jdbc
            Connection conexao = DatabaseConfig.getConnection();

            if (conexao != null && !conexao.isClosed()) {
                System.out.println("Conexão com o MySql estabelecida com sucesso via JDBC ");
                conexao.close();
            }
        } catch (Exception e) {
            System.out.println("Erro critico ao inicializar ou conectar ao banco de dados" + e.getMessage());
            e.printStackTrace();
        }
    }
}