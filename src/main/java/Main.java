import com.daniel.alertajogos.config.DatabaseConfig;
import com.daniel.alertajogos.dao.JogoDAO;
import com.daniel.alertajogos.exception.ValidacaoJogoException;
import com.daniel.alertajogos.model.Jogo;
import com.daniel.alertajogos.model.StatusJogo;
import com.daniel.alertajogos.model.Usuario;
import com.daniel.alertajogos.service.JogoService;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Alerta de Jogos...\n");

        try {
            // Inicializa o banco de dados
            DatabaseConfig.inicializarBanco();
            JogoDAO jogoDAO = new JogoDAO();
            JogoService jogoService = new JogoService();

            // =========================================================
            // BLOCO 1: BANCO DE DADOS E STREAMS
            // =========================================================
            if(jogoDAO.buscarPorTitulo("Cyberpunk 2077") == null) {
                jogoDAO.salvar(new Jogo("Cyberpunk 2077", new BigDecimal("199.90"), StatusJogo.PRECO_NORMAL));
            }
            if(jogoDAO.buscarPorTitulo("Forza Horizon 5") == null) {
                jogoDAO.salvar(new Jogo("Forza Horizon 5", new BigDecimal("249.00"), StatusJogo.EM_PROMOCAO));
            }
            if(jogoDAO.buscarPorTitulo("Hollow Knight") == null) {
                jogoDAO.salvar(new Jogo("Hollow Knight", new BigDecimal("27.99"), StatusJogo.EM_PROMOCAO));
            }

            List<Jogo> todosOsJogos = jogoDAO.buscarTodos();

            System.out.println("=== TODOS OS JOGOS NO BANCO ===");
            todosOsJogos.forEach(jogo ->
                    System.out.println("- " + jogo.getTitulo() + " (Status: " + jogo.getStatus() + ")")
            );

            System.out.println("\n=== DISPARANDO ALERTAS DE PROMOÇÃO (STREAMS) ===");
            todosOsJogos.stream()
                    .filter(jogo -> jogo.getStatus() == StatusJogo.EM_PROMOCAO)
                    .forEach(jogo -> {
                        System.out.println(" ALERTA: O jogo '" + jogo.getTitulo()
                                + "' está em promoção por apenas R$ " + jogo.getPrecoSugerido() + "!");
                    });


            // =========================================================
            // BLOCO 2: PADRÃO OBSERVER (FAVORITOS E E-MAIL)
            // =========================================================
            System.out.println("\n===  CONFIGURANDO JOGOS FAVORITOS (Padrão Observer) ===");
            Jogo diablo = new Jogo("Diablo IV", new BigDecimal("349.90"), StatusJogo.PRECO_NORMAL);
            Jogo cyberpunkMemoria = new Jogo("Cyberpunk 2077", new BigDecimal("199.90"), StatusJogo.PRECO_NORMAL);

            Usuario usuario = new Usuario("Daniel", "daniel.silva@email.com");

            cyberpunkMemoria.adicionarObservador(usuario);
            diablo.adicionarObservador(usuario);
            System.out.println("O usuário " + usuario.getNome() + " favoritou Cyberpunk 2077 e Diablo IV. Monitorando...\n");

            System.out.println("===  SIMULANDO ALTERAÇÃO DE PREÇOS NO MERCADO ===");

            System.out.println("Mudando status do Cyberpunk 2077 para PRECO_NORMAL...");
            cyberpunkMemoria.setStatus(StatusJogo.PRECO_NORMAL); // Não dispara nada

            System.out.println("Mudando status do Diablo IV para EM_PROMOCAO...");
            diablo.setStatus(StatusJogo.EM_PROMOCAO); // AQUI A MÁGICA ACONTECE!


            // =========================================================
            // BLOCO 3: TRATAMENTO DE EXCEÇÕES
            // =========================================================
            System.out.println("=== TESTANDO O TRATAMENTO DE EXCEÇÕES ===");
            Jogo jogoInvalido = new Jogo("Jogo Suspeito", new BigDecimal("-50.00"), StatusJogo.PRECO_NORMAL);
            System.out.println("Tentando salvar o jogo: " + jogoInvalido.getTitulo() + " (Preço: " + jogoInvalido.getPrecoSugerido() + ")");

            // O sistema vai barrar e pular pro Catch
            jogoService.validarNovoJogo(jogoInvalido);
            jogoDAO.salvar(jogoInvalido);

        } catch (ValidacaoJogoException e) {
            System.err.println(" BLOQUEADO PELO SISTEMA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro crítico: " + e.getMessage());
            e.printStackTrace();
        }
    }
}