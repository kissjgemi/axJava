/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import java.awt.Dimension;

/**
 *
 * @author b6dmin
 */
public class MainFrame extends javax.swing.JFrame {

    private Control c;

    public void setControl(Control c) {
        leftPanel1.setControl(c);
        middlePanel1.setControl(c);
        rightPanel1.setControl(c);
    }

    void setup() {
        System.out.println("mainFrame: "
                + this.getWidth() + "x" + this.getHeight());
        leftPanel1.setup();
        middlePanel1.setup();
        rightPanel1.setup();
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        System.out.println("MainFrame()");
        initComponents();
        super.setSize(new Dimension(leftPanel1.getWidth()
                + middlePanel1.getWidth() + rightPanel1.getWidth() + 16,
                leftPanel1.getHeight() + 38));
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }

    private void start() {
        this.setVisible(true);
        this.c = new Control(this, leftPanel1, middlePanel1, rightPanel1);
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

        leftPanel1 = new aSurface.LeftPanel();
        rightPanel1 = new aSurface.RightPanel();
        middlePanel1 = new aSurface.MiddlePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout leftPanel1Layout = new javax.swing.GroupLayout(leftPanel1);
        leftPanel1.setLayout(leftPanel1Layout);
        leftPanel1Layout.setHorizontalGroup(
            leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        leftPanel1Layout.setVerticalGroup(
            leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        getContentPane().add(leftPanel1, java.awt.BorderLayout.LINE_START);
        getContentPane().add(rightPanel1, java.awt.BorderLayout.LINE_END);
        getContentPane().add(middlePanel1, java.awt.BorderLayout.CENTER);

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
    private aSurface.LeftPanel leftPanel1;
    private aSurface.MiddlePanel middlePanel1;
    private aSurface.RightPanel rightPanel1;
    // End of variables declaration//GEN-END:variables
}
