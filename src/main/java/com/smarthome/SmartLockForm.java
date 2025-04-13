/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.smarthome;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import smart_home.SmartLockServiceGrpc;
import smart_home.SmartLockProto.SmartLockRequest;
import smart_home.SmartLockProto.SmartLockResponse;

import javax.swing.SwingUtilities;


/**
 *
 * @author lewis
 */
public class SmartLockForm extends javax.swing.JFrame {

    /**
     * Creates new form SmartLockForm
     */
    public SmartLockForm() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        lockIdField = new javax.swing.JTextField();
        lockButton = new javax.swing.JButton();
        unlockButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SMart Lock Control");

        jLabel2.setText("Lock ID:");

        lockIdField.setText("1");
        lockIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockIdFieldActionPerformed(evt);
            }
        });

        lockButton.setText("Lock");
        lockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockButtonActionPerformed(evt);
            }
        });

        unlockButton.setText("unlock");
        unlockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unlockButtonActionPerformed(evt);
            }
        });

        statusTextArea.setColumns(20);
        statusTextArea.setRows(5);
        jScrollPane1.setViewportView(statusTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lockIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lockButton)
                        .addGap(18, 18, 18)
                        .addComponent(unlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lockIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lockButton)
                    .addComponent(unlockButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lockIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lockIdFieldActionPerformed

    private void lockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockButtonActionPerformed
        // TODO add your handling code here:
        String lockId = lockIdField.getText();

    new Thread(() -> {
        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                    .usePlaintext()
                    .build();

            SmartLockServiceGrpc.SmartLockServiceBlockingStub stub = SmartLockServiceGrpc.newBlockingStub(channel);

            SmartLockRequest request = SmartLockRequest.newBuilder()
                    .setLockId(lockId)
                    .setLock(true)
                    .build();

            SmartLockResponse response = stub.controlLock(request);

            SwingUtilities.invokeLater(() -> {
                statusTextArea.append("Response: " + response.getMessage() + " (Status: " + response.getStatus() + ")\n");
            });

            channel.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                statusTextArea.append("Error: " + e.getMessage() + "\n");
            });
        }
    }).start();

        
    }//GEN-LAST:event_lockButtonActionPerformed

    private void unlockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unlockButtonActionPerformed
        // TODO add your handling code here:
        
         String lockId = lockIdField.getText();

    new Thread(() -> {
        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                    .usePlaintext()
                    .build();

            SmartLockServiceGrpc.SmartLockServiceBlockingStub stub = SmartLockServiceGrpc.newBlockingStub(channel);

            SmartLockRequest request = SmartLockRequest.newBuilder()
                    .setLockId(lockId)
                    .setLock(false)
                    .build();

            SmartLockResponse response = stub.controlLock(request);

            SwingUtilities.invokeLater(() -> {
                statusTextArea.append("Response: " + response.getMessage() + " (Status: " + response.getStatus() + ")\n");
            });

            channel.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                statusTextArea.append("Error: " + e.getMessage() + "\n");
            });
        }
    }).start();

    }//GEN-LAST:event_unlockButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SmartLockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SmartLockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SmartLockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SmartLockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmartLockForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lockButton;
    private javax.swing.JTextField lockIdField;
    private javax.swing.JTextArea statusTextArea;
    private javax.swing.JButton unlockButton;
    // End of variables declaration//GEN-END:variables
}
