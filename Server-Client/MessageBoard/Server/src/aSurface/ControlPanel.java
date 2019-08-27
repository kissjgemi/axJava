/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.MB6User;
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
    
    private final DefaultListModel<MB6User> userListModel;

    /**
     * Creates new form ControlPanel
     */
    public ControlPanel() {
        System.out.println("controlPanel()");
        this.userListModel = new DefaultListModel<>();
        initComponents();
        super.setPreferredSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
        lblTitle.setText(CONTROL_LBL_TITLE);
        lblIP.setText(CONTROL_LBL_SERVER_IP);
        lblPort.setText(CONTROL_LBL_SERVER_PORT);
        lblUserList.setText(CONTROL_LBL_USERLIST);
        setStartButtonText();
        lstUser.setModel(userListModel);
    }
    
    public void fillUserList(List<MB6User> list) {
        userListModel.clear();
        lstUser.clearSelection();
        list.forEach(userListModel::addElement);
    }
    
    public void changeLogo() {
        lblLogo.setIcon(new ImageIcon(this.getClass().getResource(ACTOR2_URL)));
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
        lblIP = new javax.swing.JLabel();
        lblUserList = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstUser = new javax.swing.JList<>();

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

        lblPort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPort.setText("jLabel2");

        lblIP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIP.setText("jLabel2");

        lblUserList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUserList.setText("jLabel2");

        lstUser.setModel(new javax.swing.AbstractListModel<MB6User>() {
            MB6User[] u = {  };
            public int getSize() { return u.length; }
            public MB6User getElementAt(int i) { return u[i]; }
        });
        lstUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstUser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(lblPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblUserList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStart))
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblUserList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
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
    }//GEN-LAST:event_btnStartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserList;
    private javax.swing.JList<MB6User> lstUser;
    // End of variables declaration//GEN-END:variables
}
