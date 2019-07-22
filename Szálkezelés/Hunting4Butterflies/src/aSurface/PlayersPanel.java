/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.Player;

/**
 *
 * @author b6dmin
 */
public class PlayersPanel extends javax.swing.JPanel {

    /**
     * Creates new form PlayerPanel
     */
    public PlayersPanel() {
        initComponents();
    }

    public static void finishGame(Player player) {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPlayer = new javax.swing.JLabel();
        txtPlayer = new javax.swing.JTextField();
        btnStartGame = new javax.swing.JButton();
        labelResults = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPlayers = new javax.swing.JList<>();
        btnSave = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setMinimumSize(new java.awt.Dimension(200, 600));
        setPreferredSize(new java.awt.Dimension(200, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPlayer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelPlayer.setText("A játékos neve:");
        add(labelPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        txtPlayer.setToolTipText("A játékos neve");
        add(txtPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 180, -1));

        btnStartGame.setText("Indítás");
        add(btnStartGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 68, 180, -1));

        labelResults.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelResults.setText("Eredmények:");
        add(labelResults, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 109, 180, -1));

        lstPlayers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstPlayers);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 132, 180, 428));

        btnSave.setText("Mentés");
        add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 566, 180, -1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPlayer;
    private javax.swing.JLabel labelResults;
    private javax.swing.JList<String> lstPlayers;
    private javax.swing.JTextField txtPlayer;
    // End of variables declaration//GEN-END:variables
}
