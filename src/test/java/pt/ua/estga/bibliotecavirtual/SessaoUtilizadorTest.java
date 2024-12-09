package pt.ua.estga.bibliotecavirtual;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessaoUtilizadorTest {
    
    public SessaoUtilizadorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
      
    }
    
    @AfterAll
    public static void tearDownClass() {
      
    }
    
    @BeforeEach
    public void setUp() {
        // Antes de cada teste, limpa a sessão
        SessaoUtilizador.limparSessao();
    }
    
    @AfterEach
    public void tearDown() {
        // Após cada teste, limpa a sessão novamente
        SessaoUtilizador.limparSessao();
    }

    @Test
    public void testSetUtilizador() {
        System.out.println("testSetUtilizador");
        
        int id = 1;
        int cargo = 2;
        boolean isFunc = true;
        String user = "arti";
        String nome = "Arti";
        String password = "arti";
        String mail = "arti@ua.pt";
        String contact = "91234567";

        SessaoUtilizador.setUtilizador(id, cargo, isFunc, user, nome, password, mail, contact);

        assertEquals(id, SessaoUtilizador.getIdUtilizador());
        assertEquals(cargo, SessaoUtilizador.getIdCargo());
        assertEquals(isFunc, SessaoUtilizador.isFuncionario());
        assertEquals(user, SessaoUtilizador.getUsername());
        assertEquals(nome, SessaoUtilizador.getNomeCompleto());
        assertEquals(password, SessaoUtilizador.getSenha());
        assertEquals(mail, SessaoUtilizador.getEmail());
        assertEquals(contact, SessaoUtilizador.getContato());
    }

    @Test
    public void testLimparSessao() {
        System.out.println("testLimparSessao");
        
        // Definir valores iniciais
        SessaoUtilizador.setUtilizador(1, 1, true, "arti", "Arti", "arti", "arti@ua.pt", "91234567");
        
        // Limpa a sessão
        SessaoUtilizador.limparSessao();

        // Verifica se os valores voltam ao padrão
        assertEquals(-1, SessaoUtilizador.getIdUtilizador());
        assertEquals(-1, SessaoUtilizador.getIdCargo());
        assertFalse(SessaoUtilizador.isFuncionario());
        assertEquals("", SessaoUtilizador.getUsername());
        assertEquals("", SessaoUtilizador.getNomeCompleto());
        assertEquals("", SessaoUtilizador.getSenha());
        assertEquals("", SessaoUtilizador.getEmail());
        assertEquals("", SessaoUtilizador.getContato());
    }

    @Test
    public void testIsLogged() {
        System.out.println("testIsLogged");
        // Como o método não está implementado, testamos se a exceção é lançada.
        assertThrows(UnsupportedOperationException.class, () -> {
            SessaoUtilizador.isLogged();
        }, "Deveria lançar UnsupportedOperationException pois não está implementado.");
    }
}