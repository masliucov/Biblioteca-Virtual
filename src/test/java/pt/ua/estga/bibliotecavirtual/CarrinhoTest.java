package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarrinhoTest {

    private Carrinho carrinhoInstance;
    private JTable tabela;

    @BeforeEach
    public void setUp() throws Exception {
        // Obtém a instância do carrinho
        carrinhoInstance = Carrinho.getInstance();
        carrinhoInstance.setVisible(false); // evita mostrar a UI durante o teste
        
        // Acesso via reflexão ao campo privado 'tabelaCarrinho'
        Field tabelaField = Carrinho.class.getDeclaredField("tabelaCarrinho");
        tabelaField.setAccessible(true);
        tabela = (JTable) tabelaField.get(carrinhoInstance);

        // Limpa o carrinho antes de cada teste
        ((DefaultTableModel) tabela.getModel()).setRowCount(0);
    }

    @AfterEach
    public void tearDown() {
        // Limpa o carrinho após cada teste, garantindo isolamento
        ((DefaultTableModel) tabela.getModel()).setRowCount(0);
    }

    @Test
    public void testGetInstance() {
        System.out.println("testGetInstance");
        Carrinho c1 = Carrinho.getInstance();
        Carrinho c2 = Carrinho.getInstance();
        // Verifica se sempre retorna a mesma instância
        assertNotNull(c1, "A instância não deveria ser nula");
        assertSame(c1, c2, "getInstance deveria retornar a mesma instância.");
    }

    @Test
    public void testAdicionarAoCarrinho() throws Exception {
        System.out.println("testAdicionarAoCarrinho");
        // Verifica que o carrinho começa vazio
        assertEquals(0, tabela.getRowCount(), "O carrinho deveria estar vazio inicialmente");

        // Adiciona um livro ao carrinho
        carrinhoInstance.adicionarAoCarrinho("12345", "Livro Teste", "10.50");

        // Verifica se o livro foi adicionado
        assertEquals(1, tabela.getRowCount(), "Deveria haver 1 livro no carrinho após adicionar");
        assertEquals("12345", tabela.getValueAt(0, 0), "O ISBN do livro adicionado deveria ser '12345'");
        assertEquals("Livro Teste", tabela.getValueAt(0, 1), "O título do livro adicionado deveria ser 'Livro Teste'");
        assertEquals("10.50", tabela.getValueAt(0, 2), "O preço do livro adicionado deveria ser '10.50'");
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        assertDoesNotThrow(() -> Carrinho.main(new String[]{}), "Chamar o main não deveria lançar exceção.");
    }
}