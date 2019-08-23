/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class GraphityPanel extends javax.swing.JPanel {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public int getGraphityHeight() {
        return this.getHeight();
    }

    public int getGraphityWidth() {
        return this.getWidth();
    }

    public void setup() {
        System.out.println("graphityPanel: "
                + this.getWidth() + "x" + this.getHeight());
    }

    public int graphityWidth() {
        return this.getWidth();
    }

    public int graphityHeight() {
        return this.getHeight();
    }

    /**
     * Creates new form GraphityPanel
     */
    public GraphityPanel() {
        System.out.println("MiddlePanel()");
        initComponents();
        super.setPreferredSize(new Dimension(GRAPHITY_WIDTH, GRAPHITY_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (state) {
            case PROLOG: {
                g.drawImage(GRAPHITY_PROLOG, 0, 0,
                        this.getWidth(), this.getHeight(), this);
                String str = GRAPHITY_PROLOG_TXT;
                g.setColor(Color.WHITE);
                g.setFont(new Font("Serif", Font.BOLD, 31));
                int width = g.getFontMetrics().stringWidth(str);
                g.drawString(str,
                        (GRAPHITY_WIDTH - width) / 2,
                        GRAPHITY_HEIGHT / 6);
                break;
            }
            case EPILOG:
            case EXIT: {
                g.drawImage(GRAPHITY_FINALE, 0, 0,
                        this.getWidth(), this.getHeight(), this);
                String str = PROCESS_STATE.EPILOG.toString();
                g.setColor(Color.WHITE);
                g.setFont(new Font("Serif", Font.BOLD, 31));
                int width = g.getFontMetrics().stringWidth(str);
                g.drawString(str,
                        (GRAPHITY_WIDTH - width) / 2,
                        GRAPHITY_HEIGHT / 6);
                break;
            }
            default: {
                g.drawImage(GRAPHITY_MAIN, 0, 0,
                        this.getWidth(), this.getHeight(), this);
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

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        c.clickOnGraphity(evt.getX(), evt.getY(), evt.getButton());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (state == PROCESS_STATE.PROLOG) {
            c.finishProlog();
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        c.startProlog(evt.getX(), evt.getY());
    }//GEN-LAST:event_formMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
