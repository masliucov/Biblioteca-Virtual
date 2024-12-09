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

public class DashboardStaffTest {
    
    private DashboardStaff dashboardStaff;

    @BeforeAll
    public static void setUpClass() {
  
    }
    
    @AfterAll
    public static void tearDownClass() {
  
    }
    
    @BeforeEach
    public void setUp() {
        dashboardStaff = new DashboardStaff();
        dashboardStaff.setVisible(false); // Evita exibir a UI durante o teste
    }
    
    @AfterEach
    public void tearDown() {
        dashboardStaff.dispose();
    }

    @Test
    public void testMain() {
        System.out.println("testMain");
        // Garante que chamar main não lança exceção
        assertDoesNotThrow(() -> DashboardStaff.main(new String[]{}),
            "Chamar o main não deveria lançar exceção.");
    }
    
    @Test
    public void testInstanciacaoETabela() throws Exception {
        System.out.println("testInstanciacaoETabela");
        assertNotNull(dashboardStaff, "A instância de DashboardStaff não deveria ser nula.");

        // Usa reflexão para aceder a tabelaStaff
        Field tabelaField = DashboardStaff.class.getDeclaredField("tabelaStaff");
        tabelaField.setAccessible(true);
        JTable tabela = (JTable) tabelaField.get(dashboardStaff);

        assertNotNull(tabela, "A tabelaStaff não deveria ser nula.");
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assertNotNull(model, "O modelo da tabela não deveria ser nulo.");

    }
}