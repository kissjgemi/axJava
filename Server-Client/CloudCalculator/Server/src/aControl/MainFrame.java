/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aGlobal.Global.*;
import java.awt.Dimension;

/**
 *
 * @author b6dmin
 */
public class MainFrame extends javax.swing.JFrame {

    private Control c;

    public void setControl(Control c) {
        controlPanel1.setControl(c);
        graphityPanel1.setControl(c);
    }

    void setup() {
        System.out.println("mainFrame: "
                + this.getWidth() + "x" + this.getHeight());
        controlPanel1.setup();
        graphityPanel1.setup();
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        System.out.println("MainFrame()");
        initComponents();
        super.setTitle(MAINFRAME_TITLE);
        super.setSize(new Dimension(controlPanel1.getWidth()
                + graphityPanel1.getWidth() + 6,
                controlPanel1.getHeight() + 28));
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }

    private void start() {
        this.setVisible(true);
        this.c = new Control(this, controlPanel1, graphityPanel1);
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

        controlPanel1 = new aSurface.ControlPanel();
        graphityPanel1 = new aSurface.GraphityPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(controlPanel1, java.awt.BorderLayout.LINE_START);
        getContentPane().add(graphityPanel1, java.awt.BorderLayout.CENTER);

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private aSurface.ControlPanel controlPanel1;
    private aSurface.GraphityPanel graphityPanel1;
    // End of variables declaration//GEN-END:variables
}