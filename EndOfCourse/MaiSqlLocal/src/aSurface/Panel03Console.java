/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Dimension;

/**
 *
 * @author b6dmin
 */
public class Panel03Console extends javax.swing.JPanel {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public void setup() {
        System.out.println("Panel03 ConsolePanel: "
                + this.getWidth() + "x" + this.getHeight());

    }

    public int consolePanelWidth() {
        return this.getWidth();
    }

    public int consolePanelHeight() {
        return this.getHeight();
    }

    /**
     * Creates new form ConsolePanel
     */
    public Panel03Console() {
        System.out.println("Panel03 ConsolePanel()");
        initComponents();
        super.setPreferredSize(new Dimension(CONSOLE_WIDTH, CONSOLE_HEIGHT));
    }

    public void setTextIntoBody(String str) {
        txtConsoleBody.setText(str + "\n");
    }

    public void insertTextIntoBody(String str) {
        txtConsoleBody.setText(txtConsoleBody.getText() + str + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        txtConsoleBody = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout(10, 10));

        txtConsoleBody.setEditable(false);
        jScrollPane3.setViewportView(txtConsoleBody);

        add(jScrollPane3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane txtConsoleBody;
    // End of variables declaration//GEN-END:variables
}
