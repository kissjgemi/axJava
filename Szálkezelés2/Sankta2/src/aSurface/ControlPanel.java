/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import static aBasis.Global.*;
import aControl.Control;
import java.awt.Dimension;
import javax.swing.DefaultListModel;

/**
 *
 * @author b6dmin
 */
public class ControlPanel extends javax.swing.JPanel {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public void setup() {
        System.out.println("controlPanel: "
                + this.getWidth() + "x" + this.getHeight());
        setButtonActivity(false);
    }

    private final DefaultListModel<String> namesList = new DefaultListModel<>();
    private final DefaultListModel<String> targetList = new DefaultListModel<>();

    /**
     * Creates new form ControlPanel
     */
    public ControlPanel() {
        System.out.println("controlPanel()");
        initComponents();
        super.setPreferredSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
        lblTitle.setText(CONTROL_LBL_TITLE);
        btnStart.setText(CONTROL_BTN_START);
        lblNames.setText(CONTROL_LBL_NAMES);
        lblTargets.setText(CONTROL_LBL_TARGETS);
        lstNames.setModel(namesList);
        lstTargets.setModel(targetList);
    }

    public void setButtonActivity(boolean b) {
        btnStart.setEnabled(b);
    }

    public void addNames(String s) {
        namesList.addElement(s);
    }

    public void addTargets(String s) {
        targetList.addElement(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTargets = new javax.swing.JList<>();
        lblTargets = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstNames = new javax.swing.JList<>();
        lblNames = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 204, 204));

        btnStart.setText("Indulás");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        lstTargets.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstTargets);

        lblTargets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTargets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTargets.setText("Kapott");

        lstNames.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstNames);

        lblNames.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNames.setText("Kért");

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("A manók");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(lblTargets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(lblNames, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(lblNames)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTargets)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        c.startMainProcess();
    }//GEN-LAST:event_btnStartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNames;
    private javax.swing.JLabel lblTargets;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<String> lstNames;
    private javax.swing.JList<String> lstTargets;
    // End of variables declaration//GEN-END:variables
}
