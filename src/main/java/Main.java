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
            // 1. Inicializa o banco de dados e roda o Flyway
            DatabaseConfig.inicializarBanco();

            // 2. Criando os jogos usando as novas regras (BigDecimal e Enum)
            // Regra de ouro que você verá na aula: Sempre crie BigDecimal passando o valor como TEXTO "As" para evitar perda de precisão!
            Jogo jogo1 = new Jogo("Cyberpunk 2077", new BigDecimal("199.90"), StatusJogo.PRECO_NORMAL);
            Jogo jogo2 = new Jogo("Forza Horizon 5", new BigDecimal("249.00"), StatusJogo.EM_PROMOCAO);

            // 3. Instanciando o nosso DAO para manipular o banco
            JogoDAO jogoDAO = new JogoDAO();

            // 4. Testando o INSERT que você acabou de criar na mão!
            System.out.println("\n--- Testando o INSERT ---");
            jogoDAO.salvar(jogo1);
            jogoDAO.salvar(jogo2);

            // 5. Testando o SELECT para ler o que está gravado lá no MySQL do XAMPP
            System.out.println("\n--- Testando o SELECT (Buscando dados do banco) ---");
            List<Jogo> listaDoBanco = jogoDAO.buscarTodos();

            for (Jogo j : listaDoBanco) {
                System.out.println("Jogo encontrado: " + j.getTitulo()
                        + " | Preço: R$ " + j.getPrecoSugerido()
                        + " | Status: " + j.getStatus());
            }

        } catch (Exception e) {
            System.err.println("Erro crítico na execução do sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}