/*package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class DatabaseUtil {

    private static final String DB_URL = "jdbc:mysql://estga-dev.ua.pt:3306/PTDA24_BD_02";
    private static String dbUsername;
    private static String dbPassword;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, dbUsername, dbPassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com a Base de Dados: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        // Configura o look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        final JComponent[] inputs = new JComponent[] {
            new JLabel("Username"),
            usernameField,
            new JLabel("Password"),
            passwordField
        };

        usernameField.addActionListener(e -> passwordField.requestFocus());
        passwordField.addActionListener(e -> submit(usernameField, passwordField));

        int result = JOptionPane.showConfirmDialog(null, inputs, "Login da Base de Dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            dbUsername = usernameField.getText().trim();
            dbPassword = new String(passwordField.getPassword()).trim();
            
            // verifica se as credenciais foram inseridas
            if (!dbUsername.isEmpty() && !dbPassword.isEmpty()) {
                java.awt.EventQueue.invokeLater(() -> new LoginInterface().setVisible(true));
            } else {
                JOptionPane.showMessageDialog(null, "Credenciais da Base de Dados não foram fornecidas. Aplicação encerrará.", "Credenciais Ausentes", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login cancelado pelo funcionario.", "Login Cancelado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void submit(JTextField usernameField, JPasswordField passwordField) {
        dbUsername = usernameField.getText().trim();
        dbPassword = new String(passwordField.getPassword()).trim();
        if (!dbUsername.isEmpty() && !dbPassword.isEmpty()) {
            java.awt.EventQueue.invokeLater(() -> new LoginInterface().setVisible(true));
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }

}
*/
//DatabaseUtil para os testes unitarios
package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseUtil {

    private static final String DB_URL = "jdbc:mysql://estga-dev.ua.pt:3306/PTDA24_BD_02";
    // Credenciais fixas para teste
    private static final String DB_USERNAME = "PTDA24_02";
    private static final String DB_PASSWORD = "Jkis$985";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Erro na conexão com a Base de Dados: " + e.getMessage(), 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            JOptionPane.showMessageDialog(null, "Conexão estabelecida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer conexão.");
        }
    }

}
