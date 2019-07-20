/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;

/**
 *
 * @author b6dmin
 */
public class MainFrame extends javax.swing.JFrame {

    private Control c;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        super.setTitle(MAINFRAME_TITLE);
        super.setLocationRelativeTo(null);
    }

    public void start() {
        c = Control.getInstance();
        c.setCONTROLLERPANEL(controllerPanel1);
        c.setGRAPHICPANEL(graphicPanel1);
        controllerPanel1.setControl(c);
        graphicPanel1.setControl(c);
        c.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controllerPanel1 = new aSurface.ControllerPanel();
        graphicPanel1 = new aSurface.GraphicPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(866, 498));
        setPreferredSize(new java.awt.Dimension(866, 498));
        getContentPane().add(controllerPanel1, java.awt.BorderLayout.LINE_START);
        getContentPane().add(graphicPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private aSurface.ControllerPanel controllerPanel1;
    private aSurface.GraphicPanel graphicPanel1;
    // End of variables declaration//GEN-END:variables
}
