/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pt.ua.estga.bibliotecavirtual;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author arti
 */
public class ListaLivrosUtilizador extends javax.swing.JFrame {

    /**
     * Creates new form ListaLivrosUtilizador
     */
    public ListaLivrosUtilizador() {
        initComponents();
        setupSearchListener();
        carregarLivros();
    }

    private void setupSearchListener() {
        pesquisar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLivros();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLivros();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarLivros();
            }
        });
    }

    private void carregarLivros() {
        DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();
        model.setRowCount(0);  // limpa a tabela antes de carregar os dados

        String query = "SELECT l.isbn, l.nome, l.autor, c.nome as categoria, l.copias, l.preco "
                + "FROM livro l "
                + "JOIN categoria c ON l.id_categoria = c.id_categoria";

        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("isbn"),
                    rs.getString("nome"),
                    rs.getString("autor"),
                    rs.getString("categoria"),
                    rs.getInt("copias"),
                    rs.getDouble("preco")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar livros: " + e.getMessage(), "Erro da Base de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarLivros() {
        String texto = pesquisar.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tabelaLivros.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tabelaLivros.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
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
        tabelaLivros = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        pesquisar = new javax.swing.JTextField();
        voltar = new javax.swing.JButton();
        sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Lista de livros");

        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Título", "Autor", "Categoria", "Cópias", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaLivrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaLivros);

        jLabel2.setText("Pesquisar");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(sair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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


    private void tabelaLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaLivrosMouseClicked
        if (evt.getClickCount() == 2 && tabelaLivros.getSelectedRow() != -1) {
            int row = tabelaLivros.getSelectedRow();
            String isbn = tabelaLivros.getValueAt(row, 0).toString();
            VerLivro verLivroWindow = new VerLivro(isbn);
            verLivroWindow.setVisible(true);
        }
    }//GEN-LAST:event_tabelaLivrosMouseClicked

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
            java.util.logging.Logger.getLogger(ListaLivrosUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaLivrosUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaLivrosUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaLivrosUtilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardUtilizador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pesquisar;
    private javax.swing.JButton sair;
    private javax.swing.JTable tabelaLivros;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
