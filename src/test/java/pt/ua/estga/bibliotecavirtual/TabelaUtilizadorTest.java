package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TabelaUtilizadorTest {
    
    private TabelaUtilizador tabelaUtilizadorInstance;

    @BeforeAll
    public static void setUpClass() {
 
    }
    
    @AfterAll
    public static void tearDownClass() {
 
    }
    
    @BeforeEach
    public void setUp() {
        // Cria a instância da classe antes de cada teste
        tabelaUtilizadorInstance = new TabelaUtilizador();
        tabelaUtilizadorInstance.setVisible(false); // Evita exibir a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        // Fecha a janela após cada teste
        tabelaUtilizadorInstance.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Verifica se chamar o main não lança exceção
        assertDoesNotThrow(() -> TabelaUtilizador.main(new String[]{}),
            "Chamar main não deveria lançar exceção.");
    }

    @Test
    public void testInstanciacaoETabela() throws Exception {
        System.out.println("testInstanciacaoETabela");
        assertNotNull(tabelaUtilizadorInstance, "A instância de TabelaUtilizador não deveria ser nula.");

        // Acede a tabela via reflexão
        Field tabelaField = TabelaUtilizador.class.getDeclaredField("tabelaUtilizador");
        tabelaField.setAccessible(true);
        JTable tabela = (JTable) tabelaField.get(tabelaUtilizadorInstance);
        assertNotNull(tabela, "A tabelaUtilizador não deveria ser nula.");

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assertNotNull(model, "O modelo da tabela não deveria ser nulo.");
    }

    @Test
    public void testCampoPesquisa() throws Exception {
        System.out.println("testCampoPesquisa");

        // Acede o campo de pesquisa via reflexão
        Field pesquisarField = TabelaUtilizador.class.getDeclaredField("pesquisar");
        pesquisarField.setAccessible(true);
        JTextField pesquisarTextField = (JTextField) pesquisarField.get(tabelaUtilizadorInstance);
        assertNotNull(pesquisarTextField, "O campo pesquisar não deveria ser nulo.");
    }
}