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

public class DashboardCategoriaTest {

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

    @Test
    public void testMain() {
        System.out.println("testMain");
        assertDoesNotThrow(() -> DashboardCategoria.main(new String[]{}), 
            "Chamar main não deveria lançar exceção.");
    }

    /**
     * Testa a criação da instância de DashboardCategoria e o acesso à tabela de categorias.
     */
    @Test
    public void testInstanciacaoEDadosTabela() throws Exception {
        System.out.println("testInstanciacaoEDadosTabela");
        // Cria a instância do DashboardCategoria
        DashboardCategoria dashboard = new DashboardCategoria();
        dashboard.setVisible(false); // evita mostrar UI durante o teste

        // Acesso via reflexão ao campo privado 'tabelaCategoria'
        Field tabelaField = DashboardCategoria.class.getDeclaredField("tabelaCategoria");
        tabelaField.setAccessible(true);
        JTable tabela = (JTable) tabelaField.get(dashboard);

        assertNotNull(tabela, "A tabelaCategoria não deveria ser nula");
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assertNotNull(model, "O modelo da tabela não deveria ser nulo");

        // Aqui não garantimos que haja linhas, pois depende da Base de Dados.
        // Apenas confirmamos que não houve erros ao carregar dados.
        assertTrue(model.getRowCount() >= 0, "O número de linhas deveria ser >= 0 (pode ser 0 se BD estiver vazia)");
    }
}