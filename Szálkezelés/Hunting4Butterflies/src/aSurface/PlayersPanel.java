/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import static aBasis.Global.*;
import aBasis.Player;
import aControl.Control;
import aModelPackage.SortableListModel;
import java.awt.Font;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class PlayersPanel extends javax.swing.JPanel {

    private final SortableListModel<Player> playerModel = new SortableListModel<>();

    private Control c;
    private String errortext = "";

    public void setControl(Control c) {
        this.c = c;
    }

    /**
     * Creates new form PlayerPanel
     */
    public PlayersPanel() {
        initComponents();
        btnStartGame.setEnabled(false);
        btnSave.setEnabled(false);
        lstPlayers.setModel(playerModel);
        rdbtnHU.setFont(rdbtnHU.getFont().deriveFont(Font.BOLD));
    }

    public void setTextLocale() {
        errortext = rBundle.getString("ERROR_MESSAGE");
        labelPlayer.setText(rBundle.getString("LBL_PLAYER_TXT"));
        labelResults.setText(rBundle.getString("LBL_RESULTS_TXT"));
        btnStartGame.setText(rBundle.getString("BTN_STARTGAME_TXT"));
        btnSave.setText(rBundle.getString("BTN_SAVE_TXT"));
        pointtext = rBundle.getString("POINT_TXT");
    }

    public void finishGame(Player player) {
        btnStartGame.setEnabled(true);
        addResult(player);
        btnSave.setEnabled(true);
    }

    private void addResult(Player p) {
        lstPlayers.clearSelection();
        if (playerModel.contains(p)) {
            playerModel.removeElement(p);
        }
        playerModel.addElement(p, true);
    }

    public void listPlayers(List<Player> playersList) {
        playersList.forEach((player) -> {
            playerModel.addElement(player, true);
        });
        btnStartGame.setEnabled(true);
    }

    private void useLanguage() {
        rBundle = ResourceBundle.getBundle(BUNDLE, locale);
        c.setLocaleBundle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        labelPlayer = new javax.swing.JLabel();
        txtPlayer = new javax.swing.JTextField();
        btnStartGame = new javax.swing.JButton();
        labelResults = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPlayers = new javax.swing.JList<>();
        btnSave = new javax.swing.JButton();
        rdbtnHU = new javax.swing.JRadioButton();
        rsbtnEN = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setMinimumSize(new java.awt.Dimension(200, 600));
        setPreferredSize(new java.awt.Dimension(200, 600));

        labelPlayer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelPlayer.setText("A játékos neve:");

        txtPlayer.setToolTipText("A játékos neve");

        btnStartGame.setText("Indítás");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });

        labelResults.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelResults.setText("Eredmények:");

        lstPlayers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstPlayers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstPlayers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPlayersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstPlayers);

        btnSave.setText("Mentés");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbtnHU);
        rdbtnHU.setText("magyar");
        rdbtnHU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/Hungary.png"))); // NOI18N
        rdbtnHU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnHUActionPerformed(evt);
            }
        });

        buttonGroup1.add(rsbtnEN);
        rsbtnEN.setText("english");
        rsbtnEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/Great-britain.png"))); // NOI18N
        rsbtnEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsbtnENActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPlayer)
                    .addComponent(txtPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelResults, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdbtnHU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rsbtnEN)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnStartGame)
                .addGap(18, 18, 18)
                .addComponent(labelResults)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnHU)
                    .addComponent(rsbtnEN))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        String name = txtPlayer.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this.getParent(), errortext);
        } else {
            Player p = new Player(name);
            JOptionPane.showMessageDialog(this.getParent(), p.getNAME());
            c.startGame(p);
            btnStartGame.setEnabled(false);
        }
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void rdbtnHUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnHUActionPerformed
        locale = new Locale("hu", "HU");
        rdbtnHU.setFont(rdbtnHU.getFont().deriveFont(Font.BOLD));
        rsbtnEN.setFont(rsbtnEN.getFont().deriveFont(Font.PLAIN));
        useLanguage();
    }//GEN-LAST:event_rdbtnHUActionPerformed

    private void rsbtnENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsbtnENActionPerformed
        locale = new Locale("en", "GB");
        rdbtnHU.setFont(rdbtnHU.getFont().deriveFont(Font.PLAIN));
        rsbtnEN.setFont(rsbtnEN.getFont().deriveFont(Font.BOLD));
        useLanguage();
    }//GEN-LAST:event_rsbtnENActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        c.saveDatas(playerModel.getModell());
        btnSave.setEnabled(false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lstPlayersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPlayersValueChanged
        if (!lstPlayers.isSelectionEmpty()) {
            Player p = playerModel.getElementAt(lstPlayers.getSelectedIndex());
            txtPlayer.setText(p.getNAME());
        }
    }//GEN-LAST:event_lstPlayersValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnStartGame;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPlayer;
    private javax.swing.JLabel labelResults;
    private javax.swing.JList<String> lstPlayers;
    private javax.swing.JRadioButton rdbtnHU;
    private javax.swing.JRadioButton rsbtnEN;
    private javax.swing.JTextField txtPlayer;
    // End of variables declaration//GEN-END:variables
}
