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

/**
 *
 * @author arti
 */
public class AtualizarPerfilUtilizador extends javax.swing.JFrame {

    /**
     * Creates new form AtualizarPerfilUtilizador
     */
    public AtualizarPerfilUtilizador() {
        initComponents();
        preencherDadosUtilizador();
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

    private void preencherDadosUtilizador() {
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
                    JOptionPane.showMessageDialog(this, "Senha atualizada com sucesso!");
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
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar contacto!");
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
        voltar = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        eliminarConta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Atualizar Perfil Utilizador");

        eliminarConta.setText("Eliminar conta");
        eliminarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(verPassword))
                            .addGroup(layout.createSequentialGroup()
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
                                    .addComponent(inserirPassword)
                                    .addComponent(inserirEmail)
                                    .addComponent(inserirContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(atualizarUsername)
                                    .addComponent(atualizarNome)
                                    .addComponent(atualizarPassword)
                                    .addComponent(atualizarEmail)
                                    .addComponent(atualizarContacto))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(sair)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(eliminarConta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(sair)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addComponent(eliminarConta)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        // Cria uma instância da interface DashboardUtilizador
        DashboardUtilizador registerWindow = new DashboardUtilizador();

        // Mete a janela DashboardUtilizador visível
        registerWindow.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        // Cria uma instância da interface LoginInterface
        LoginInterface registerWindow = new LoginInterface();

        // Mete a janela LoginInterface visível
        registerWindow.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_sairActionPerformed

    private void eliminarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarContaActionPerformed
       // abre uma caixa de diálogo para confirmar a exclusão da conta
    int confirm = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja desativar a sua conta? Esta ação é reversível apenas pelo administrador.",
            "Confirmar Desativação",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

    // se o utilizador confirmar, prossegue com a desativação
    if (confirm == JOptionPane.YES_OPTION) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "UPDATE utilizador SET isActive = false WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, SessaoUtilizador.getIdUtilizador());
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Conta desativada com sucesso!");

                    // fecha a janela atual e abre a interface de login
                    this.dispose();
                    java.awt.EventQueue.invokeLater(() -> {
                        new LoginInterface().setVisible(true);
                    });

                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao desativar conta!");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro de SQL: " + ex.getMessage(), "Erro de Base de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_eliminarContaActionPerformed

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
            java.util.logging.Logger.getLogger(AtualizarPerfilUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarPerfilUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarPerfilUtilizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarContacto;
    private javax.swing.JButton atualizarEmail;
    private javax.swing.JButton atualizarNome;
    private javax.swing.JButton atualizarPassword;
    private javax.swing.JButton atualizarUsername;
    private javax.swing.JButton eliminarConta;
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
