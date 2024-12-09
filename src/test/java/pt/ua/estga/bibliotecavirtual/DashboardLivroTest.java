package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardLivroTest {
    
    private DashboardLivro dashboardLivro;

    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
        
    }
    
    @BeforeEach
    public void setUp() {
        // Cria a instância do DashboardLivro antes de cada teste
        dashboardLivro = new DashboardLivro();
        dashboardLivro.setVisible(false); // Evita mostrar a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        // Fecha a janela após cada teste
        dashboardLivro.dispose();
    }

    /**
     * Testa o método main, verificando se pode ser chamado sem lançar exceções.
     */
    @Test
    public void testMain() {
        System.out.println("testMain");
        assertDoesNotThrow(() -> DashboardLivro.main(new String[]{}), 
            "Chamar o main não deveria lançar exceção.");
    }
    
    /**
     * Testa a instanciação da classe e o acesso à tabela de livros.
     */
    @Test
    public void testInstanciacaoETabela() throws Exception {
        System.out.println("testInstanciacaoETabela");
        assertNotNull(dashboardLivro, "A instância de DashboardLivro não deveria ser nula.");

        // Acesso via reflexão ao campo privado 'tabelaLivros'
        Field tabelaField = DashboardLivro.class.getDeclaredField("tabelaLivros");
        tabelaField.setAccessible(true);
        JTable tabela = (JTable) tabelaField.get(dashboardLivro);

        assertNotNull(tabela, "A tabelaLivros não deveria ser nula.");
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assertNotNull(model, "O modelo da tabela não deveria ser nulo.");

    }
}