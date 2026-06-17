import com.daniel.alertajogos.model.Jogo;
import com.daniel.alertajogos.model.Usuario;

public class Main {
    static void main(String[] args) {
        Usuario player1 = new Usuario("Daniel", "danielalves.scarparo@gmail.com");

        Jogo jogo1 = new Jogo("Cyberpunk 2077", 199.90, "preco_normal");
        Jogo jogo2 = new Jogo("Forza Horizon 5", 249.00, "em_promocao");

        System.out.println("sistema de alertas Iniciados");
        System.out.println("Usuario: " + player1.getNome());
        System.out.println("Monitorando: " + jogo1.getTitulo() + " - Status: " +jogo1.getStatus());
        System.out.println("Monitorando: " + jogo2.getTitulo() + " - Status: " +jogo2.getStatus());
    }
}
