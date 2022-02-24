/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domain.Seller;
import form.model.TableModelLoggedSellers;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controler;
import settings.SettingsConnection;
import threads.ServerStart;

/**
 *
 * @author Rados
 */
public class FormServer extends javax.swing.JFrame {

    private ServerStart serverStart;
    
    /**
     * Creates new form ServerskaForma
     */
    public FormServer() {
        initComponents();
        //this.setExtendedState(MAXIMIZED_BOTH);
        populateForm();
        Controler.getInstance().setFormServer(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoggedSellers = new javax.swing.JTable();
        lblLoggedSellers = new javax.swing.JLabel();
        menuServer = new javax.swing.JMenuBar();
        jmSettings = new javax.swing.JMenu();
        jmiConfigurationDatebase = new javax.swing.JMenuItem();
        jmiConfiguratioConnection = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblStatus.setText("Status:");

        txtStatus.setEnabled(false);

        btnStart.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStart.setText("Pokreni server");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStop.setText("Zaustavi server");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        tblLoggedSellers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLoggedSellers);

        lblLoggedSellers.setText("Ulogovani prodavci:");

        jmSettings.setText("Podešavanja");

        jmiConfigurationDatebase.setText("Konfiguracija baze");
        jmiConfigurationDatebase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConfigurationDatebaseActionPerformed(evt);
            }
        });
        jmSettings.add(jmiConfigurationDatebase);

        jmiConfiguratioConnection.setText("Konfiguracija konekcije");
        jmiConfiguratioConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConfiguratioConnectionActionPerformed(evt);
            }
        });
        jmSettings.add(jmiConfiguratioConnection);

        menuServer.add(jmSettings);

        setJMenuBar(menuServer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblStatus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtStatus))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnStart)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                            .addComponent(btnStop))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(lblLoggedSellers))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(lblLoggedSellers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        serverStart = new ServerStart();
        Controler.getInstance().setServerStart(serverStart);
        serverStart.start();
        
        int port = Integer.parseInt(SettingsConnection.getInstance().getProperty("port"));
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(FormServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (serverStart.isActive()) {
            txtStatus.setText("Server je pokrenut na portu:" + port);
            btnStop.setEnabled(true);
            btnStart.setEnabled(false);
            jmiConfiguratioConnection.setEnabled(false);
            jmiConfigurationDatebase.setEnabled(false);
        } else {
            txtStatus.setText("Server ne moze da se pokrene! Greška");
        }

    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        stopServer();
    }//GEN-LAST:event_btnStopActionPerformed

    private void jmiConfigurationDatebaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfigurationDatebaseActionPerformed
        ConfigurationDatebase frm = new ConfigurationDatebase(this, true);
        frm.setLocationRelativeTo(null);
        frm.pack();
        frm.setVisible(true);
    }//GEN-LAST:event_jmiConfigurationDatebaseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (Controler.getInstance().getServerStart() != null
                && Controler.getInstance().getServerStart().isActive()) {
            stopServer();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jmiConfiguratioConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfiguratioConnectionActionPerformed
        ConfigurationConnection frm = new ConfigurationConnection(this, true);
        frm.setLocationRelativeTo(null);
        frm.pack();
        frm.setVisible(true);
    }//GEN-LAST:event_jmiConfiguratioConnectionActionPerformed

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
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormServer().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu jmSettings;
    private javax.swing.JMenuItem jmiConfiguratioConnection;
    private javax.swing.JMenuItem jmiConfigurationDatebase;
    private javax.swing.JLabel lblLoggedSellers;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenuBar menuServer;
    private javax.swing.JTable tblLoggedSellers;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables

    private void populateForm() {
        setLocationRelativeTo(null);
        setStatus();
        setTable();
    }
    
    public void setTable(){
        tblLoggedSellers.setModel(new TableModelLoggedSellers());
    }

    private void setStatus() {
        txtStatus.setText("Server nije pokrenut.");
    }

    private void stopServer() {
        Controler.getInstance().stopServer();
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
        jmiConfiguratioConnection.setEnabled(true);
        jmiConfigurationDatebase.setEnabled(true);
        txtStatus.setText("Server je zaustavljen.");
    }

    public void addLoggedSeller(Seller s) {
        TableModelLoggedSellers tmls = (TableModelLoggedSellers) tblLoggedSellers.getModel();
        tmls.add(s);
    }

    public void logout(Seller s) {
        TableModelLoggedSellers tmls = (TableModelLoggedSellers) tblLoggedSellers.getModel();
        tmls.remove(s);
    }

}