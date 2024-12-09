package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AtualizarPerfilUtilizadorTest {
    
    private AtualizarPerfilUtilizador atualizarPerfil;

    @BeforeAll
    public static void setUpClass() {
        // SessaoUtilizador.setUtilizador(1, 1, false, "arti", "Arti", "arti", "arti@ua.p", "91234567");
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Cria uma instância antes de cada teste
        atualizarPerfil = new AtualizarPerfilUtilizador();
        atualizarPerfil.setVisible(false); // evita mostrar a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        // Limpeza após cada teste
        atualizarPerfil.dispose();
    }

    /**
     * Testa o método main, garantindo que pode ser chamado sem exceções.
     */
    @Test
    public void testMain() {
        System.out.println("testMain");
        // Apenas asseguramos que chamar o main não lança exceção.
        assertDoesNotThrow(() -> AtualizarPerfilUtilizador.main(new String[]{}), 
            "Chamar main não deveria lançar exceção.");
    }
    
    /**
     * Testa a instanciação da classe e o acesso aos campos principais via reflexão.
     */
    @Test
    public void testInstanciacaoECampos() throws Exception {
        System.out.println("testInstanciacaoECampos");
        assertNotNull(atualizarPerfil, "A instância de AtualizarPerfilUtilizador não deveria ser nula.");

        // Acede o campo inserirUsername via reflexão
        Field usernameField = AtualizarPerfilUtilizador.class.getDeclaredField("inserirUsername");
        usernameField.setAccessible(true);
        JTextField inserirUsername = (JTextField) usernameField.get(atualizarPerfil);
        assertNotNull(inserirUsername, "O campo inserirUsername não deveria ser nulo.");

        // Acede o campo inserirPassword via reflexão
        Field passwordField = AtualizarPerfilUtilizador.class.getDeclaredField("inserirPassword");
        passwordField.setAccessible(true);
        JPasswordField inserirPassword = (JPasswordField) passwordField.get(atualizarPerfil);
        assertNotNull(inserirPassword, "O campo inserirPassword não deveria ser nulo.");
    }
}