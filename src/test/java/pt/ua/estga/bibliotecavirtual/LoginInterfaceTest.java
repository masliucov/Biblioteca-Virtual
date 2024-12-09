package pt.ua.estga.bibliotecavirtual;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginInterfaceTest {
    
    private LoginInterface loginInterface;
    
    @BeforeAll
    public static void setUpClass() {
   
    }
    
    @AfterAll
    public static void tearDownClass() {
   
    }
    
    @BeforeEach
    public void setUp() {
        // Cria a instância antes de cada teste
        loginInterface = new LoginInterface();
        loginInterface.setVisible(false); // Evita mostrar UI no teste
    }
    
    @AfterEach
    public void tearDown() {
        // Fecha a janela após cada teste
        loginInterface.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Verifica se o main pode ser chamado sem lançar exceção
        assertDoesNotThrow(() -> LoginInterface.main(new String[]{}),
            "Chamar main não deveria lançar exceções.");
    }

    @Test
    public void testGetInserirLogin() {
        System.out.println("testGetInserirLogin");
        JTextField inserirLogin = loginInterface.getInserirLogin();
        assertNotNull(inserirLogin, "O campo inserirLogin não deveria ser nulo.");
    }

    @Test
    public void testGetInserirPassword() {
        System.out.println("testGetInserirPassword");
        JPasswordField inserirPassword = loginInterface.getInserirPassword();
        assertNotNull(inserirPassword, "O campo inserirPassword não deveria ser nulo.");
    }

    @Test
    public void testGetEntrar() {
        System.out.println("testGetEntrar");
        JButton entrar = loginInterface.getEntrar();
        assertNotNull(entrar, "O botão entrar não deveria ser nulo.");
    }

    /**
     * Testa o login com as credenciais conhecidas:
     * Username ou email: "arti" ou "arti@ua.pt"
     * Password: "arti"
     * Este teste espera que, se a BD estiver configurada e o utilizador existir,
     * nenhuma exceção seja lançada. Caso contrário, pode apenas verificar que o código
     * lida graciosamente com credenciais erradas.
     */
    @Test
    public void testLoginComCredenciaisValidas() {
        System.out.println("testLoginComCredenciaisValidas");
        
        JTextField inserirLogin = loginInterface.getInserirLogin();
        JPasswordField inserirPassword = loginInterface.getInserirPassword();
        JButton entrar = loginInterface.getEntrar();
        
        // Define as credenciais. Pode usar "arti" ou "arti@ua.pt".
        inserirLogin.setText("arti");
        inserirPassword.setText("arti");
        
        // Simula o clique no botão "Entrar"
        assertDoesNotThrow(() -> {
            // Cria um evento de clique simulado
            entrar.doClick(); 
        }, "Clicar em Entrar com credenciais válidas não deveria lançar exceção.");
    }
}