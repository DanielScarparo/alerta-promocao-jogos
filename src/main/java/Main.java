import com.daniel.alertajogos.config.DatabaseConfig;
import com.daniel.alertajogos.dao.JogoDAO;
import com.daniel.alertajogos.model.Jogo;
import com.daniel.alertajogos.model.StatusJogo;
import com.daniel.alertajogos.model.Usuario;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Alerta de Jogos...");

        try {
            // Inicializa o banco de dados e roda o Flyway
            DatabaseConfig.inicializarBanco();

            // Instanciando o nosso DAO para manipular o banco
            JogoDAO jogoDAO = new JogoDAO();

            // Simulando a inserção de alguns jogos (só vai inserir se não existir, para não duplicar toda hora)
            if(jogoDAO.buscarPorTitulo("Cyberpunk 2077") == null) {
                jogoDAO.salvar(new Jogo("Cyberpunk 2077", new BigDecimal("199.90"), StatusJogo.PRECO_NORMAL));
            }
            if(jogoDAO.buscarPorTitulo("Forza Horizon 5") == null) {
                jogoDAO.salvar(new Jogo("Forza Horizon 5", new BigDecimal("249.00"), StatusJogo.EM_PROMOCAO));
            }
            if(jogoDAO.buscarPorTitulo("Hollow Knight") == null) {
                jogoDAO.salvar(new Jogo("Hollow Knight", new BigDecimal("27.99"), StatusJogo.EM_PROMOCAO));
            }

            // Pegando TODOS os jogos do banco de dados (A nossa Collection / Lista)
            List<Jogo> todosOsJogos = jogoDAO.buscarTodos();

            System.out.println("\n=== TODOS OS JOGOS NO BANCO ===");
            todosOsJogos.forEach(jogo ->
                    System.out.println("- " + jogo.getTitulo() + " (Status: " + jogo.getStatus() + ")")
            );

            System.out.println("\n===  DISPARANDO ALERTAS DE PROMOÇÃO  ===");

            // A MÁGICA DA STREAM API AQUI!
            // Vamos filtrar a lista para pegar APENAS os jogos em promoção
            todosOsJogos.stream()
                    .filter(jogo -> jogo.getStatus() == StatusJogo.EM_PROMOCAO) // Filtro inteligente (Lambda)
                    .forEach(jogo -> {
                        // Para cada jogo filtrado, montamos o alerta
                        System.out.println(" ALERTA: O jogo '" + jogo.getTitulo()
                                + "' está em promoção por apenas R$ " + jogo.getPrecoSugerido() + "!");
                    });

        } catch (Exception e) {
            System.err.println("Erro crítico na execução do sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}