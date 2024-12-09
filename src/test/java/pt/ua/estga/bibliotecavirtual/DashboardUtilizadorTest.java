package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JButton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardUtilizadorTest {

    private DashboardUtilizador dashboardUtilizador;

    @BeforeAll
    public static void setUpClass() {
  
    }

    @AfterAll
    public static void tearDownClass() {
  
    }

    @BeforeEach
    public void setUp() {
        // Cria a instância da classe antes de cada teste
        dashboardUtilizador = new DashboardUtilizador();
        dashboardUtilizador.setVisible(false); // Evita exibir a UI durante o teste
    }

    @AfterEach
    public void tearDown() {
        // Fecha a janela após cada teste
        dashboardUtilizador.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Verifica se chamar o método main não lança exceção
        assertDoesNotThrow(() -> DashboardUtilizador.main(new String[]{}),
            "Chamar o main não deveria lançar exceção.");
    }

    @Test
    public void testInstanciacao() {
        System.out.println("testInstanciacao");
        assertNotNull(dashboardUtilizador, "A instância de DashboardUtilizador não deveria ser nula.");
    }

    @Test
    public void testBotoesExistem() throws Exception {
        System.out.println("testBotoesExistem");

        // Verifica se o botão listaLivros existe
        Field listaLivrosField = DashboardUtilizador.class.getDeclaredField("listaLivros");
        listaLivrosField.setAccessible(true);
        JButton listaLivrosButton = (JButton) listaLivrosField.get(dashboardUtilizador);
        assertNotNull(listaLivrosButton, "O botão listaLivros não deveria ser nulo.");

        // Verifica se o botão verHistorico existe
        Field verHistoricoField = DashboardUtilizador.class.getDeclaredField("verHistorico");
        verHistoricoField.setAccessible(true);
        JButton verHistoricoButton = (JButton) verHistoricoField.get(dashboardUtilizador);
        assertNotNull(verHistoricoButton, "O botão verHistorico não deveria ser nulo.");

        // Verifica se o botão verCarrinho existe
        Field verCarrinhoField = DashboardUtilizador.class.getDeclaredField("verCarrinho");
        verCarrinhoField.setAccessible(true);
        JButton verCarrinhoButton = (JButton) verCarrinhoField.get(dashboardUtilizador);
        assertNotNull(verCarrinhoButton, "O botão verCarrinho não deveria ser nulo.");
        
         // Verifica se o botão editarPerfil existe
        Field editarPerfilField = DashboardUtilizador.class.getDeclaredField("editarPerfil");
        editarPerfilField.setAccessible(true);
        JButton editarPerfilButton = (JButton) editarPerfilField.get(dashboardUtilizador);
        assertNotNull(editarPerfilButton, "O botão editarPerfil não deveria ser nulo.");
    }
}