/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.Fish;
import static aBasis.Global.*;
import aControl.Control;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class ControllerPanel extends javax.swing.JPanel {

    DefaultListModel<Fish> fishListModel = new DefaultListModel<>();
    private final Icon VOICE_ON
            = new ImageIcon(getClass().getResource(ICON_VOICE_ON));
    private final Icon VOICE_OFF
            = new ImageIcon(getClass().getResource(ICON_VOICE_OFF));

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    /**
     * Creates new form ControllerPanel
     */
    public ControllerPanel() {
        initComponents();
        listFishes.setModel(fishListModel);
        setMusicButton(true);
    }

    public void setMusicButton(boolean state) {
        if (state) {
            btnVoice.setIcon(VOICE_ON);
        } else {
            btnVoice.setIcon(VOICE_OFF);
        }
    }

    public void listFishes(List<Fish> fishList) {
        fishListModel.clear();
        for (Fish fish : fishList) {
            fishListModel.addElement(fish);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listFishes = new javax.swing.JList<>();
        btnSelection = new javax.swing.JButton();
        btnVoice = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 204, 204));
        setMinimumSize(new java.awt.Dimension(150, 460));
        setPreferredSize(new java.awt.Dimension(150, 460));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Halak");

        listFishes.setModel(new javax.swing.AbstractListModel<Fish>() {
            Fish[] f = {};
            public int getSize() { return f.length; }
            public Fish getElementAt(int i) { return f[i]; }
        });
        jScrollPane1.setViewportView(listFishes);

        btnSelection.setText("Kiválasztás");
        btnSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectionActionPerformed(evt);
            }
        });

        btnVoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/kepek/hangbe.png"))); // NOI18N
        btnVoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSelection)
                .addGap(18, 18, 18)
                .addComponent(btnVoice)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectionActionPerformed
        List<Fish> f = listFishes.getSelectedValuesList();
        for (Fish fish : f) {
            c.swimFish(fish);
            fishListModel.removeElement(fish);
        }
    }//GEN-LAST:event_btnSelectionActionPerformed

    private void btnVoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoiceActionPerformed
        c.playOnOff();
    }//GEN-LAST:event_btnVoiceActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelection;
    private javax.swing.JButton btnVoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Fish> listFishes;
    // End of variables declaration//GEN-END:variables
}
