/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aControl.Control;

/**
 *
 * @author b6dmin
 */
public class Hallowien extends javax.swing.JPanel {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
        warriors1.setControl(c);
        battle1.setControl(c);
    }

    public Warriors getWarriors() {
        return warriors1;
    }

    public Battle getBattle() {
        return battle1;
    }

    /**
     * Creates new form Hallowien
     */
    public Hallowien() {
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

        warriors1 = new aSurface.Warriors();
        battle1 = new aSurface.Battle();

        setLayout(new java.awt.BorderLayout());
        add(warriors1, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout battle1Layout = new javax.swing.GroupLayout(battle1);
        battle1.setLayout(battle1Layout);
        battle1Layout.setHorizontalGroup(
            battle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        battle1Layout.setVerticalGroup(
            battle1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        add(battle1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private aSurface.Battle battle1;
    private aSurface.Warriors warriors1;
    // End of variables declaration//GEN-END:variables
}
