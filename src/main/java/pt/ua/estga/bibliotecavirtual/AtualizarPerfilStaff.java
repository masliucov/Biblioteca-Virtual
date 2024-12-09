/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author arti
 */
public class AtualizarPerfilStaff extends javax.swing.JFrame {

    /**
     * Creates new form AtualizarPerfilStaff
     */
    public AtualizarPerfilStaff() {
        initComponents();
        preencherDadosStaff();
        verConteudoCheckBox();
        configurarBotoesAtualizacao();
    }

    private void verConteudoCheckBox() {
        verPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (verPassword.isSelected()) {
            inserirPassword.setEchoChar((char) 0); // mostra a password
        } else {
            inserirPassword.setEchoChar('•'); // oculta a password
        }
    }

    private void preencherDadosStaff() {
        inserirUsername.setText(SessaoUtilizador.getUsername());
        inserirNome.setText(SessaoUtilizador.getNomeCompleto());
        inserirPassword.setText(SessaoUtilizador.getSenha());
        inserirEmail.setText(SessaoUtilizador.getEmail());
        inserirContacto.setText(SessaoUtilizador.getContato());
    }

    private void configurarBotoesAtualizacao() {
        atualizarUsername.addActionListener(evt -> atualizarUsername());
        atualizarNome.addActionListener(evt -> atualizarNome());
        atualizarPassword.addActionListener(evt -> atualizarSenha());
        atualizarEmail.addActionListener(evt -> atualizarEmail());
        atualizarContacto.addActionListener(evt -> atualizarContato());
    }

    private void atualizarUsername() {
        String novoUsername = inserirUsername.getText();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "UPDATE utilizador SET username = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, novoUsername);
                pstmt.setInt(2, SessaoUtilizador.getIdUtilizador());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Username atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar username!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro de SQL: " + ex.getMessage());
        }
    }

    private void atualizarNome() {
        String novoNome = inserirNome.getText();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "UPDATE utilizador SET nome = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, novoNome);
                pstmt.setInt(2, SessaoUtilizador.getIdUtilizador());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Nome atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar nome!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro de SQL: " + ex.getMessage());
        }
    }

    private void atualizarSenha() {
        String novaSenha = new String(inserirPassword.getPassword());
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "UPDATE utilizador SET password = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, novaSenha);
                pstmt.setInt(2, SessaoUtilizador.getIdUtilizador());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Password atualizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar senha!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro de SQL: " + ex.getMessage());
        }
    }

    private void atualizarEmail() {
        String novoEmail = inserirEmail.getText().trim();
        if (!novoEmail.equals(SessaoUtilizador.getEmail())) {
            // verifica se o email já existe na base de dados
            try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT EXISTS(SELECT 1 FROM utilizador WHERE email = ?) AS 'exists'")) {
                pstmt.setString(1, novoEmail);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next() && rs.getInt("exists") == 1) {
                        JOptionPane.showMessageDialog(this, "Este email já está em uso. Por favor, escolha outro.", "Erro de Email", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // se o email for único, atualiza
                        atualizarCampo("email", novoEmail);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro de SQL: " + ex.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "O novo email é o mesmo que o atual. Nenhuma atualização necessária.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualizarContato() {
        String novoContato = inserirContacto.getText();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "UPDATE utilizador SET contacto = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, novoContato);
                pstmt.setInt(2, SessaoUtilizador.getIdUtilizador());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Contacto atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar contactoo");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro de SQL: " + ex.getMessage());
        }
    }

    private void atualizarCampo(String campo, String valor) {
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement("UPDATE utilizador SET " + campo + " = ? WHERE id = ?")) {
            pstmt.setString(1, valor);
            pstmt.setInt(2, SessaoUtilizador.getIdUtilizador());
            int afetados = pstmt.executeUpdate();
            if (afetados > 0) {
                JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar perfil. Nenhuma mudança realizada.", "Erro de Atualização", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar " + campo + ": " + ex.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        voltar = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        inserirUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inserirNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        inserirPassword = new javax.swing.JPasswordField();
        verPassword = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        inserirEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        inserirContacto = new javax.swing.JTextField();
        atualizarUsername = new javax.swing.JButton();
        atualizarNome = new javax.swing.JButton();
        atualizarPassword = new javax.swing.JButton();
        atualizarEmail = new javax.swing.JButton();
        atualizarContacto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Atualizar Perfill Staff");

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        jLabel2.setText("Username");

        jLabel3.setText("Nome");

        jLabel4.setText("Password");

        verPassword.setText("Ver Password");

        jLabel5.setText("Email");

        jLabel6.setText("Contacto");

        atualizarUsername.setText("Atualizar");

        atualizarNome.setText("Atualizar");

        atualizarPassword.setText("Atualizar");

        atualizarEmail.setText("Atualizar");

        atualizarContacto.setText("Atualizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(verPassword))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(81, 81, 81)
                .addComponent(sair)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inserirUsername)
                    .addComponent(inserirNome)
                    .addComponent(inserirPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(inserirEmail)
                    .addComponent(inserirContacto, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(atualizarUsername)
                    .addComponent(atualizarNome)
                    .addComponent(atualizarPassword)
                    .addComponent(atualizarEmail)
                    .addComponent(atualizarContacto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sair)
                            .addComponent(voltar))))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inserirUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inserirNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarNome))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inserirPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(inserirEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inserirContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarContacto))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        // Cria uma instância da interface LoginInterface
        LoginInterface registerWindow = new LoginInterface();

        // Mete a janela LoginInterface visível
        registerWindow.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_sairActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        // Cria uma instância da interface DashboardAdmin
        DashboardAdmin registerWindow = new DashboardAdmin();

        // Mete a janela DashboardAdmin visível
        registerWindow.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_voltarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarPerfilStaff().setVisible(true);
            }
        });
    }
    /*
    public JTextField getInserirUsernameField() {
    return inserirUsername;
}
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarContacto;
    private javax.swing.JButton atualizarEmail;
    private javax.swing.JButton atualizarNome;
    private javax.swing.JButton atualizarPassword;
    private javax.swing.JButton atualizarUsername;
    private javax.swing.JTextField inserirContacto;
    private javax.swing.JTextField inserirEmail;
    private javax.swing.JTextField inserirNome;
    private javax.swing.JPasswordField inserirPassword;
    private javax.swing.JTextField inserirUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton sair;
    private javax.swing.JCheckBox verPassword;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
