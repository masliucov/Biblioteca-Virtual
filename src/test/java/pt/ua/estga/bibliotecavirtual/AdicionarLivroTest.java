package pt.ua.estga.bibliotecavirtual;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdicionarLivroTest {
    
    public AdicionarLivroTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Testa o método isbnExiste da classe AdicionarLivro.
     * Aqui verificamos ISBN vazio, assumindo que não existe na base de dados.
     */
    @Test
    public void testIsbnExiste() {
        System.out.println("isbnExiste");
        String isbn = "";
        AdicionarLivro instance = new AdicionarLivro();
        boolean expResult = false;
        boolean result = instance.isbnExiste(isbn);
        assertEquals(expResult, result, "ISBN vazio não deveria existir na Base de Dados.");
    }

    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        // Apenas verifica se o método pode ser chamado sem exceção.
        assertDoesNotThrow(() -> AdicionarLivro.main(args), "Chamar main não deveria lançar exceções.");
    }
    
}