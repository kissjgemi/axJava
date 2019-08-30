/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.ZenUser;
import aControl.Control;
import static aGlobal.Global.*;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class ControlPanel extends javax.swing.JPanel {

    private boolean isStarted = false;

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
        System.out.println("controlPanel: "
                + panelWidth() + "x" + panelHeight());
    }

    private void setStartButtonText() {
        if (isStarted) {
            btnStart.setText(CONTROL_BTN_START_TRUE);
        } else {
            btnStart.setText(CONTROL_BTN_START_FALSE);
        }
    }

    private final DefaultListModel<ZenUser> userListModel;

    /**
     * Creates new form ControlPanel
     */
    public ControlPanel() {
        System.out.println("controlPanel()");
        this.userListModel = new DefaultListModel<>();
        initComponents();
        super.setPreferredSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
        lblTitle.setText(CONTROL_LBL_TITLE);
        lblIP1.setText(CONTROL_LBL_SERVER_IP1);
        lblIP2.setText(CONTROL_LBL_SERVER_IP2);
        lblIP3.setText(CONTROL_LBL_SERVER_IP3);
        lblPort.setText(CONTROL_LBL_SERVER_PORT);
        lblUserList.setText(CONTROL_LBL_USERLIST);
        setStartButtonText();
        lstUser.setModel(userListModel);
    }

    public void fillUserList(List<ZenUser> list) {
        userListModel.clear();
        lstUser.clearSelection();
        list.forEach(userListModel::addElement);
    }

    public void changeLogo() {
        if (isStarted) {
            lblLogo.setIcon(new ImageIcon(this.getClass().getResource(ACTOR2_URL)));
        } else {
            lblLogo.setIcon(new ImageIcon(this.getClass().getResource(ACTOR1_URL)));
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

        lblLogo = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblIP3 = new javax.swing.JLabel();
        lblUserList = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstUser = new javax.swing.JList<>();
        lblIP2 = new javax.swing.JLabel();
        lblIP1 = new javax.swing.JLabel();

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aSource/actor1.gif"))); // NOI18N

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStart.setText("jButton1");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("jLabel2");

        lblPort.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPort.setText("jLabel4");

        lblIP3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIP3.setText("jLabel3");

        lblUserList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUserList.setText("jLabel2");

        lstUser.setModel(new javax.swing.AbstractListModel<aBasis.ZenUser>() {
            aBasis.ZenUser[] u = {  };
            public int getSize() { return u.length; }
            public aBasis.ZenUser getElementAt(int i) { return u[i]; }
        });
        lstUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstUser);

        lblIP2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIP2.setText("jLabel2");

        lblIP1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIP1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPort, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblIP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIP1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblUserList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIP1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIP2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIP3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStart))
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (isStarted) {
            isStarted = false;
            c.stopServer();
        } else {
            isStarted = true;
            c.startServer();
        }
        setStartButtonText();
        changeLogo();
    }//GEN-LAST:event_btnStartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIP1;
    private javax.swing.JLabel lblIP2;
    private javax.swing.JLabel lblIP3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserList;
    private javax.swing.JList<aBasis.ZenUser> lstUser;
    // End of variables declaration//GEN-END:variables
}
