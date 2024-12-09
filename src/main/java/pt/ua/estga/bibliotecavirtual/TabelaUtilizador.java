/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pt.ua.estga.bibliotecavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author arti
 */
public class TabelaUtilizador extends javax.swing.JFrame {

    /**
     * Creates new form TabelaUtilizador
     */
    public TabelaUtilizador() {
        initComponents();
        setupListeners();
        carregarDadosNaTabela();
    }

private void carregarDadosNaTabela() {
    DefaultTableModel model = (DefaultTableModel) tabelaUtilizador.getModel();
    model.setRowCount(0);

    String query = "SELECT u.id, u.username, u.nome, u.email, u.contacto, u.isActive "
            + "FROM utilizador u "
            + "LEFT JOIN funcionario f ON u.id = f.id_utilizador "
            + "WHERE f.id_utilizador IS NULL "
            + "ORDER BY u.id";

    try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String contacto = rs.getString("contacto");
            boolean isActive = rs.getBoolean("isActive");
            String situacao = isActive ? "Ativo" : "Desativada";
            model.addRow(new Object[]{id, username, nome, email, contacto, situacao});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar utilizadores: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    private void setupListeners() {
        pesquisar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String text = pesquisar.getText().trim();
                if (text.isEmpty()) {
                    carregarDadosNaTabela();
                } else {
                    filterData(text);
                }
            }
        });

        desativarUtilizador.addActionListener(evt -> desativarUtilizador());
        ativarUtilizador.addActionListener(evt -> ativarUtilizador());
    }

private void filterData(String text) {
    DefaultTableModel model = (DefaultTableModel) tabelaUtilizador.getModel();
    model.setRowCount(0);

    String query = "SELECT u.id, u.username, u.nome, u.email, u.contacto, u.isActive "
            + "FROM utilizador u "
            + "LEFT JOIN funcionario f ON u.id = f.id_utilizador "
            + "WHERE f.id_utilizador IS NULL AND ("
            + "u.username LIKE ? OR u.nome LIKE ? OR u.email LIKE ? OR u.contacto LIKE ? OR "
            + "CASE WHEN u.isActive = 1 THEN 'Ativo' ELSE 'Desativada' END LIKE ?) "
            + "ORDER BY u.id";

    try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
        // parâmetros de pesquisa
        pstmt.setString(1, "%" + text + "%");
        pstmt.setString(2, "%" + text + "%");
        pstmt.setString(3, "%" + text + "%");
        pstmt.setString(4, "%" + text + "%");
        pstmt.setString(5, "%" + text + "%");

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String contacto = rs.getString("contacto");
            boolean isActive = rs.getBoolean("isActive");
            String situacao = isActive ? "Ativo" : "Desativada";
            model.addRow(new Object[]{id, username, nome, email, contacto, situacao});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao filtrar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
private void desativarUtilizador() {
    int userId = Integer.parseInt(idUtilizador1.getText().trim());

    // desativa a conta do utilizador
    String query = "UPDATE utilizador SET isActive = 0 WHERE id = ? AND NOT EXISTS ("
            + "SELECT 1 FROM funcionario WHERE id_utilizador = ?)";

    try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setInt(2, userId);

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(this, "Utilizador desativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarDadosNaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao desativar utilizador ou utilizador é um funcionário.", "Falha", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao desativar utilizador: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

private void ativarUtilizador() {
    int userId = Integer.parseInt(idUtilizador2.getText().trim());

    // ativa a conta do utilizador
    String query = "UPDATE utilizador SET isActive = 1 WHERE id = ? AND NOT EXISTS ("
            + "SELECT 1 FROM funcionario WHERE id_utilizador = ?)";

    try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setInt(2, userId);

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(this, "Utilizador ativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarDadosNaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao ativar utilizador ou utilizador é um funcionário.", "Falha", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao ativar utilizador: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUtilizador = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        pesquisar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        idUtilizador1 = new javax.swing.JTextField();
        desativarUtilizador = new javax.swing.JButton();
        voltar = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        idUtilizador2 = new javax.swing.JTextField();
        ativarUtilizador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Tabela utilizador");

        tabelaUtilizador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "username", "nome", "e-mail", "contacto", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaUtilizador);

        jLabel2.setText("Pesquisar");

        jLabel3.setText("ID do utilizador para desativar");

        desativarUtilizador.setText("Desativar");

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

        jLabel4.setText("ID do utilizador para ativar");

        ativarUtilizador.setText("Ativar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(102, 102, 102)
                        .addComponent(sair))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idUtilizador1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(idUtilizador2))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(desativarUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ativarUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voltar)
                    .addComponent(sair)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idUtilizador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desativarUtilizador))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idUtilizador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ativarUtilizador))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        // Cria uma instância da interface DashboardAdmin
        DashboardAdmin registerWindow = new DashboardAdmin();

        // Mete a janela DashboardAdmin visível
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
            java.util.logging.Logger.getLogger(TabelaUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelaUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelaUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelaUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TabelaUtilizador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ativarUtilizador;
    private javax.swing.JButton desativarUtilizador;
    private javax.swing.JTextField idUtilizador1;
    private javax.swing.JTextField idUtilizador2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pesquisar;
    private javax.swing.JButton sair;
    private javax.swing.JTable tabelaUtilizador;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
