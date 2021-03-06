/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aControl.Control;
import static aGlobal.Global.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author b6dmin
 */
public class GraphityPanel extends javax.swing.JPanel {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public int panelWidth() {
        return this.getWidth();
    }

    public int panelHeight() {
        return this.getHeight();
    }

    public void setup() {
        System.out.println("graphityPanel: "
                + panelWidth() + "x" + panelHeight());
    }

    private final DefaultListModel<String> msgeListModel;

    /**
     * Creates new form GraphityPanel
     */
    public GraphityPanel() {
        System.out.println("MiddlePanel()");
        this.msgeListModel = new DefaultListModel<>();
        initComponents();
        super.setPreferredSize(new Dimension(GRAPHITY_WIDTH, GRAPHITY_HEIGHT));
        lstMessages.setModel(msgeListModel);
        msgeListModel.clear();
    }

    public void addToList(String str) {
        msgeListModel.addElement(str);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (state) {
            case PROLOG: {
                g.drawImage(GRAPHITY_PROLOG, 0, 0,
                        this.getWidth(), this.getHeight(), this);
                g.drawImage(LOGO, LOGO_X, LOGO_Y,
                        this.getWidth(), LOGO_HEIGHT, this);
                g.drawImage(DECOR, DECOR_X, DECOR_Y,
                        DECOR_WIDTH, DECOR_HEIGHT, this);
                String str = GRAPHITY_PROLOG_TXT;
                g.setColor(Color.WHITE);
                g.setFont(new Font("Serif", Font.BOLD, 31));
                int width = g.getFontMetrics().stringWidth(str);
                int height = g.getFontMetrics().getHeight();
                g.drawString(str,
                        (GRAPHITY_WIDTH - width) / 2, 3 * height / 4);
                break;
            }
            case EPILOG:
            case EXIT: {
                g.drawImage(GRAPHITY_FINALE, 0, 0,
                        this.getWidth(), this.getHeight(), this);
                String str = PROCESS_STATE.EPILOG.toString();
                g.setColor(Color.BLUE);
                g.setFont(new Font("Serif", Font.BOLD, 31));
                int width = g.getFontMetrics().stringWidth(str);
                int height = g.getFontMetrics().getHeight();
                g.drawString(str, (GRAPHITY_WIDTH - width) / 2, 3 * height / 4);
                break;
            }
            default: {
                g.drawImage(GRAPHITY_MAIN, 0, 0,
                        this.getWidth(), this.getHeight(), this);
                g.drawImage(LOGO, LOGO_X, LOGO_Y,
                        this.getWidth(), LOGO_HEIGHT, this);
            }
        }
        if (c != null) {
            c.drawGraphity(g);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        lstMessages = new javax.swing.JList<>();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        lstMessages.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstMessages.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstMessages);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        c.clickOnGraphity(evt.getX(), evt.getY(), evt.getButton());
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstMessages;
    // End of variables declaration//GEN-END:variables
}
