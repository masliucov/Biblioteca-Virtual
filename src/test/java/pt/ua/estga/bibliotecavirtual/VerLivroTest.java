package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JLabel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VerLivroTest {
    
    private VerLivro verLivroInstance;
    private static final String TEST_ISBN = "105"; // ISBN de um livro disponivel
    
    @BeforeAll
    public static void setUpClass() {

    }
    
    @AfterAll
    public static void tearDownClass() {

    }
    
    @BeforeEach
    public void setUp() {
        // Cria a instância da classe antes de cada teste
        verLivroInstance = new VerLivro(TEST_ISBN);
        verLivroInstance.setVisible(false); // Evita mostrar a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        // Fecha a janela após cada teste
        verLivroInstance.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Verifica se chamar main não lança exceções
        assertDoesNotThrow(() -> VerLivro.main(new String[]{}),
            "Chamar o main não deveria lançar exceção.");
    }

    @Test
    public void testInstanciacao() {
        System.out.println("testInstanciacao");
        assertNotNull(verLivroInstance, "A instância de VerLivro não deveria ser nula.");
    }

    @Test
    public void testComponentesUI() throws Exception {
        System.out.println("testComponentesUI");

        // Acede campos via reflexão para verificar se não são nulos
        Field jTituloField = VerLivro.class.getDeclaredField("jTitulo");
        jTituloField.setAccessible(true);
        JLabel jTituloLabel = (JLabel) jTituloField.get(verLivroInstance);
        assertNotNull(jTituloLabel, "O label jTitulo não deveria ser nulo.");

        Field jAutorField = VerLivro.class.getDeclaredField("jAutor");
        jAutorField.setAccessible(true);
        JLabel jAutorLabel = (JLabel) jAutorField.get(verLivroInstance);
        assertNotNull(jAutorLabel, "O label jAutor não deveria ser nulo.");

    }

    @Test
    public void testSetLookAndFeel() {
        System.out.println("testSetLookAndFeel");
        assertDoesNotThrow(() -> VerLivro.setLookAndFeel(),
            "Chamar setLookAndFeel não deveria lançar exceção.");
    }
}