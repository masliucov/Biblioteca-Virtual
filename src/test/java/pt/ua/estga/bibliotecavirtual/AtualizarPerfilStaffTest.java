package pt.ua.estga.bibliotecavirtual;

import java.lang.reflect.Field;
import javax.swing.JTextField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AtualizarPerfilStaffTest {
    
    private AtualizarPerfilStaff atualizarPerfilStaff;
    
    @BeforeAll
    static void setUpClass() {
        // Configura o SessaoUtilizador com dados de teste
        SessaoUtilizador.limparSessao();
        SessaoUtilizador.setUtilizador(
            1,         // idUtilizador
            1,         // idCargo
            true,      // funcionario
            "arti",    // username
            "Arti",    // nomeCompleto
            "arti",    // senha
            "arti@ua.p", // email
            "91234567" // contacto
        );
    }
    
    @AfterAll
    static void tearDownClass() {
        SessaoUtilizador.limparSessao();
    }
    
    @BeforeEach
    void setUp() {
        // Instancia a classe antes de cada teste
        atualizarPerfilStaff = new AtualizarPerfilStaff();
        // Evita exibir a janela durante o teste
        atualizarPerfilStaff.setVisible(false);
    }
    
    @AfterEach
    void tearDown() {
        // Fecha a janela após cada teste
        atualizarPerfilStaff.dispose();
    }

    @Test
    void testInstanciacao() {
        assertNotNull(atualizarPerfilStaff, "A instância de AtualizarPerfilStaff não deveria ser nula.");
    }

        @Test
    public void testPreenchimentoDadosStaff() throws Exception {
        AtualizarPerfilStaff atualizarPerfilStaff = new AtualizarPerfilStaff();
        atualizarPerfilStaff.setVisible(false);

        Field usernameField = AtualizarPerfilStaff.class.getDeclaredField("inserirUsername");
        usernameField.setAccessible(true);
        JTextField inserirUsername = (JTextField) usernameField.get(atualizarPerfilStaff);
        
        assertEquals("arti", inserirUsername.getText());
    }
    
    @Test
    void testMain() {
        assertTrue(true, "O teste do método main é apenas um placeholder.");
    }
}