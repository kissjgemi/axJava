/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import java.awt.Dimension;

/**
 *
 * @author b6dmin
 */
public class MainFrame extends javax.swing.JFrame {

    private Control c;

    public void setControl(Control c) {
        panel01Control1.setControl(c);
        panel02Results1.setControl(c);
        panel03Console1.setControl(c);
    }

    void setup() {
        System.out.println("mainFrame: "
                + this.getWidth() + "x" + this.getHeight());
        panel01Control1.setup();
        panel02Results1.setup();
        panel03Console1.setup();
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        System.out.println("MainFrame()");
        initComponents();
        super.setTitle(MAINFRAME_TITLE);
        super.setSize(new Dimension(panel01Control1.getWidth() + 16,
                panel01Control1.getHeight()
                + panel02Results1.getHeight()
                + panel03Console1.getHeight() + 38));
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }

    private void start() {
        this.setVisible(true);
        this.c = new Control(this,
                panel01Control1, panel02Results1, panel03Console1);
        setControl(c);
        c.setup();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel03Console1 = new aSurface.Panel03Console();
        panel01Control1 = new aSurface.Panel01Control();
        panel02Results1 = new aSurface.Panel02Results();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(panel03Console1, java.awt.BorderLayout.SOUTH);
        getContentPane().add(panel01Control1, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(panel02Results1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private aSurface.Panel01Control panel01Control1;
    private aSurface.Panel02Results panel02Results1;
    private aSurface.Panel03Console panel03Console1;
    // End of variables declaration//GEN-END:variables
}
