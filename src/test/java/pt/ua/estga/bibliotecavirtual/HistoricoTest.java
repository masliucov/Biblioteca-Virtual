package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HistoricoTest {

    private Historico historico;

    @BeforeAll
    public static void setUpClass() {
  
    }

    @AfterAll
    public static void tearDownClass() {
  
    }

    @BeforeEach
    public void setUp() {
        // Cria a instância do Historico com idUtilizador = 18
        historico = new Historico(18);
        historico.setVisible(false); // Evita mostrar a UI durante o teste
    }

    @AfterEach
    public void tearDown() {
        // Fecha a janela após o teste
        historico.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Garante que chamar main não lança exceção
        assertDoesNotThrow(() -> Historico.main(new String[]{}),
            "Chamar main não deveria lançar exceção.");
    }

    @Test
    public void testInstanciacao() {
        System.out.println("testInstanciacao");
        assertNotNull(historico, "A instância de Historico não deveria ser nula.");
    }

    @Test
    public void testComponentesUI() throws Exception {
        System.out.println("testComponentesUI");
        
        // Verifica o comboBox idCarrinho via reflexão
        Field idCarrinhoField = Historico.class.getDeclaredField("idCarrinho");
        idCarrinhoField.setAccessible(true);
        JComboBox<?> idCarrinhoCombo = (JComboBox<?>) idCarrinhoField.get(historico);
        assertNotNull(idCarrinhoCombo, "O comboBox idCarrinho não deveria ser nulo.");

        // Verifica a tabela tabelaHistorico via reflexão
        Field tabelaField = Historico.class.getDeclaredField("tabelaHistorico");
        tabelaField.setAccessible(true);
        JTable tabela = (JTable) tabelaField.get(historico);
        assertNotNull(tabela, "A tabelaHistorico não deveria ser nula.");
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assertNotNull(model, "O modelo da tabela não deveria ser nulo.");
        
        // Verifica o label jTotal via reflexão
        Field jTotalField = Historico.class.getDeclaredField("jTotal");
        jTotalField.setAccessible(true);
        JLabel jTotalLabel = (JLabel) jTotalField.get(historico);
        assertNotNull(jTotalLabel, "O label jTotal não deveria ser nulo.");
    }
}