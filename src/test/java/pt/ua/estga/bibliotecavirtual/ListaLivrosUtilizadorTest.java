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

public class ListaLivrosUtilizadorTest {
    
    private ListaLivrosUtilizador listaLivrosUI;

    @BeforeAll
    public static void setUpClass() {
   
    }
    
    @AfterAll
    public static void tearDownClass() {
   
    }
    
    @BeforeEach
    public void setUp() {
        // Cria a instância da classe antes de cada teste
        listaLivrosUI = new ListaLivrosUtilizador();
        listaLivrosUI.setVisible(false); // Evita exibir a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        listaLivrosUI.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Verifica se chamar o main não lança exceções
        assertDoesNotThrow(() -> ListaLivrosUtilizador.main(new String[]{}),
            "Chamar o main não deveria lançar exceção.");
    }

    @Test
    public void testInstanciacao() {
        System.out.println("testInstanciacao");
        assertNotNull(listaLivrosUI, "A instância de ListaLivrosUtilizador não deveria ser nula.");
    }

    @Test
    public void testComponentesUI() throws Exception {
        System.out.println("testComponentesUI");

        // Verifica o campo de pesquisa via reflexão
        Field pesquisarField = ListaLivrosUtilizador.class.getDeclaredField("pesquisar");
        pesquisarField.setAccessible(true);
        JTextField pesquisarTextField = (JTextField) pesquisarField.get(listaLivrosUI);
        assertNotNull(pesquisarTextField, "O campo pesquisar não deveria ser nulo.");

        // Verifica a tabela via reflexão
        Field tabelaField = ListaLivrosUtilizador.class.getDeclaredField("tabelaLivros");
        tabelaField.setAccessible(true);
        JTable tabela = (JTable) tabelaField.get(listaLivrosUI);
        assertNotNull(tabela, "A tabelaLivros não deveria ser nula.");
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assertNotNull(model, "O modelo da tabela não deveria ser nulo.");
    }
}