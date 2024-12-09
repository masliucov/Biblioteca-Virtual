package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseUtilTest {
    
    @BeforeAll
    public static void setUpClass() {
        // Nada a configurar antes de todos os testes
    }
    
    @AfterAll
    public static void tearDownClass() {
        // Nada a limpar após todos os testes
    }
    
    @BeforeEach
    public void setUp() {
        // Nada a preparar antes de cada teste
    }
    
    @AfterEach
    public void tearDown() {
        // Nada a limpar após cada teste
    }

    @Test
    public void testGetConnection() {
        System.out.println("testGetConnection");
        Connection conn = DatabaseUtil.getConnection();
        assertNotNull(conn, "A conexão não deveria ser nula se as credenciais e a Base de Dados estiverem acessíveis.");
        try {
            assertFalse(conn.isClosed(), "A conexão não deveria estar fechada.");
        } catch (Exception e) {
            fail("Exceção ao verificar o estado da conexão: " + e.getMessage());
        }
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Não chamamos o main realmente, pois envolve UI. Verificamos apenas que podemos chamá-lo sem exceções.
        assertDoesNotThrow(() -> DatabaseUtil.main(new String[]{}),
            "Chamar o main não deveria lançar exceções.");
    }   
}