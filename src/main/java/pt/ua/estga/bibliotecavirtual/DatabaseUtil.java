package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DatabaseUtil {

    private static String dbUsername;
    private static String dbPassword;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://estga-dev.ua.pt:3306/PTDA24_BD_02";
            return DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com a Base de Dados: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        // configura o look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        }

        // pede as credenciais da base de dados
        dbUsername = JOptionPane.showInputDialog(null, "Insira o username da base de dados:", "Login da Base de Dados", JOptionPane.PLAIN_MESSAGE);
        dbPassword = JOptionPane.showInputDialog(null, "Insira a password da base de dados:", "Login da Base de Dados", JOptionPane.PLAIN_MESSAGE);

        // verifica se as credenciais foram inseridas
        if (dbUsername != null && dbPassword != null) {
            // abre a interface de login se as credenciais foram fornecidas
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LoginInterface().setVisible(true);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Credenciais da Base de Dados não foram fornecidas. Aplicação encerrará.", "Credenciais Ausentes", JOptionPane.ERROR_MESSAGE);
        }
    }
}
