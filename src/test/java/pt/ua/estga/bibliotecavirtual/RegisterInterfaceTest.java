package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterInterfaceTest {
    
    private RegisterInterface registerInterface;
    
    @BeforeAll
    public static void setUpClass() {
  
    }
    
    @AfterAll
    public static void tearDownClass() {
  
    }
    
    @BeforeEach
    public void setUp() {
        registerInterface = new RegisterInterface();
        registerInterface.setVisible(false); // Evita exibir a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        registerInterface.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        assertDoesNotThrow(() -> RegisterInterface.main(new String[]{}),
            "Chamar main não deveria lançar exceção.");
    }

    @Test
    public void testCriarContaComDadosValidos() throws Exception {
        System.out.println("testCriarContaComDadosValidos");

        // Acede os campos via reflexão
        Field inserirUsernameField = RegisterInterface.class.getDeclaredField("inserirUsername");
        inserirUsernameField.setAccessible(true);
        JTextField inserirUsername = (JTextField) inserirUsernameField.get(registerInterface);

        Field inserirEmailField = RegisterInterface.class.getDeclaredField("inserirEmail");
        inserirEmailField.setAccessible(true);
        JTextField inserirEmail = (JTextField) inserirEmailField.get(registerInterface);

        Field inserirPasswordField = RegisterInterface.class.getDeclaredField("inserirPassword");
        inserirPasswordField.setAccessible(true);
        JPasswordField inserirPassword = (JPasswordField) inserirPasswordField.get(registerInterface);

        Field criarContaField = RegisterInterface.class.getDeclaredField("criarConta");
        criarContaField.setAccessible(true);
        JButton criarConta = (JButton) criarContaField.get(registerInterface);

        // Preenche os campos com dados de teste
        inserirUsername.setText("testX");
        inserirEmail.setText("testX@ua.pt");
        inserirPassword.setText("testX");

        // Simula o clique em "Criar conta"
        assertDoesNotThrow(() -> {
            criarConta.doClick();
        }, "Clicar em criarConta com dados de teste não deveria lançar exceção.");
    }
}