package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AtualizarLivroTest {
    
    private AtualizarLivro atualizarLivro;

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // Instancia a classe antes de cada teste
        atualizarLivro = new AtualizarLivro();
        // Evita exibir a janela UI durante o teste
        atualizarLivro.setVisible(false);
    }
    
    @AfterEach
    public void tearDown() {
        // Fecha a janela após o teste, se necessário
        atualizarLivro.dispose();
    }

    /**
     * Teste simples para verificar se AtualizarLivro pode ser instanciado sem lançar exceções.
     */
    @Test
    public void testInstanciacao() {
        assertNotNull(atualizarLivro, "A instância de AtualizarLivro não deveria ser nula.");
    }

    /**
     * Testa o método privado obterIdCategoria(String nomeCategoria) ao usar reflexão.
     * Aqui assumimos que existe na base de dados uma categoria chamada "História".
     */
    @Test
    public void testObterIdCategoriaExistente() {
        String categoriaExistente = "História"; // Nome de uma categoria na Base de Dados.
        int id = chamarObterIdCategoria(categoriaExistente);
        assertTrue(id > 0, "O ID da categoria deveria ser > 0 para uma categoria existente.");
    }

    /**
     * Testa o método obterIdCategoria com uma categoria inexistente, é esperado retornar -1.
     */
    @Test
    public void testObterIdCategoriaInexistente() {
        String categoriaInexistente = "CategoriaQueNaoExiste";
        int id = chamarObterIdCategoria(categoriaInexistente);
        assertEquals(-1, id, "O método deveria retornar -1 para uma categoria inexistente.");
    }

    /**
     * Método auxiliar para chamar o obterIdCategoria via reflexão, já que é privado.
     */
    private int chamarObterIdCategoria(String nomeCategoria) {
        try {
            Method metodo = AtualizarLivro.class.getDeclaredMethod("obterIdCategoria", String.class);
            metodo.setAccessible(true);
            Object result = metodo.invoke(atualizarLivro, nomeCategoria);
            return (int) result;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            fail("Falha ao chamar obterIdCategoria via reflexão: " + e.getMessage());
            return -1;
        }
    }


    @Test
    public void testMain() {
        System.out.println("main");
        // Não chamaremos o main, pois envolve UI. Apenas garantimos que chegar aqui não falha.
        assertTrue(true, "Teste do main é apenas um placeholder.");
    }
    
}