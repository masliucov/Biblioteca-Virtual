package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JButton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardAdminTest {
    
    private DashboardAdmin dashboardAdminInstance;

    @BeforeAll
    public static void setUpClass() {

    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Cria a instância da classe antes de cada teste
        dashboardAdminInstance = new DashboardAdmin();
        dashboardAdminInstance.setVisible(false); // Evita mostrar a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        // Fecha a janela após cada teste
        dashboardAdminInstance.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        assertDoesNotThrow(() -> DashboardAdmin.main(new String[]{}),
            "Chamar o main não deveria lançar exceção.");
    }

    @Test
    public void testInstanciacao() {
        System.out.println("testInstanciacao");
        assertNotNull(dashboardAdminInstance, "A instância de DashboardAdmin não deveria ser nula.");
    }

    @Test
    public void testBotoesExistem() throws Exception {
        System.out.println("testBotoesExistem");

        // Verifica se o botão 'dashboardLivro' existe via reflexão
        Field dashboardLivroField = DashboardAdmin.class.getDeclaredField("dashboardLivro");
        dashboardLivroField.setAccessible(true);
        JButton dashboardLivroButton = (JButton) dashboardLivroField.get(dashboardAdminInstance);
        assertNotNull(dashboardLivroButton, "O botão dashboardLivro não deveria ser nulo.");
    }
}