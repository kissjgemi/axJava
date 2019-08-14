/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import static aBasis.Global.*;
import aBasis.Sprite;
import aControl.Control;
import aModel.SortableListModel;
import aModel.SortedComboBoxModel;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author b6dmin
 */
public class ControlPanel extends javax.swing.JPanel {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    private String comboSelected;

    public void setTextLocale() {
        lblList.setText(rBundle.getString("LBL_LIST_TXT"));
        lblCombo.setText(rBundle.getString("LBL_COMBO_TXT"));
        btnTribute.setText(rBundle.getString("BTN_TRIBUTE_TXT"));
        btnFinish.setText(rBundle.getString("BTN_FINISH_TXT"));
        comboModel.removeAllElements();

        comboSelected = rBundle.getString("CATEGORIE_CHOICE");
        comboModel.addElement(comboSelected);
        comboSelected = rBundle.getString("CATEGORIE_PEACE");
        comboModel.addElement(comboSelected);
        comboSelected = rBundle.getString("CATEGORIE_LITERATUR");
        comboModel.addElement(comboSelected);
        comboSelected = rBundle.getString("CATEGORIE_PHYSICS");
        comboModel.addElement(comboSelected);
        comboSelected = rBundle.getString("CATEGORIE_MEDICAL");
        comboModel.addElement(comboSelected);
        comboSelected = rBundle.getString("CATEGORIE_ECONOMIC");
        comboModel.addElement(comboSelected);
        comboSelected = rBundle.getString("CATEGORIE_CHEMISTRY");
        comboModel.addElement(comboSelected);
    }

    public void setup() {
        System.out.println("controlPanel: "
                + this.getWidth() + "x" + this.getHeight());
        setButtonActivity(false);
    }

    SortableListModel<Sprite> listModel = new SortableListModel<>();
    SortedComboBoxModel<String> comboModel = new SortedComboBoxModel<>();

    /**
     * Creates new form ControlForm
     */
    public ControlPanel() {
        System.out.println("ControlPanel()");
        initComponents();
        super.setPreferredSize(new Dimension(CONTROL_WIDTH, CONTROL_HEIGHT));
        lstHome.setModel(listModel);
        comboList.setModel(comboModel);
    }

    public void setButtonTribute(boolean b) {
        btnTribute.setEnabled(b);
    }

    public void setButtonFinish(boolean b) {
        btnFinish.setEnabled(b);
    }

    public void setComboList(boolean b) {
        comboList.setEnabled(b);
    }

    public void setButtonActivity(boolean b) {
        setButtonTribute(b);
        setButtonFinish(b);
        setComboList(b);
    }

    public void clearList() {
        listModel.clear();
        this.repaint();
    }

    public void add2List(Sprite s) {
        listModel.addElement(s, true);
    }

    public void deleteFromList(Sprite s) {
        listModel.removeElement(s);
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
        rdbHU = new javax.swing.JRadioButton();
        rdbEN = new javax.swing.JRadioButton();
        lblList = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHome = new javax.swing.JList<>();
        lblCombo = new javax.swing.JLabel();
        btnTribute = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        comboList = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 204));

        buttonGroup1.add(rdbHU);
        rdbHU.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbHU.setSelected(true);
        rdbHU.setText("magyar");
        rdbHU.setIcon(new javax.swing.ImageIcon("G:\\aB6main\\000_Axjava\\Szálkezelés2\\NobelPrice\\src\\aSource\\aHungary.png")); // NOI18N
        rdbHU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbHUActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbEN);
        rdbEN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdbEN.setText("english");
        rdbEN.setIcon(new javax.swing.ImageIcon("G:\\aB6main\\000_Axjava\\Szálkezelés2\\NobelPrice\\src\\aSource\\aGreat-britain.png")); // NOI18N
        rdbEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbENActionPerformed(evt);
            }
        });

        lblList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblList.setText("jLabel1");

        lstHome.setFont(new java.awt.Font("Tahoma", 0, 12));
        lstHome.setVisibleRowCount(14);
        jScrollPane1.setViewportView(lstHome);

        lblCombo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCombo.setText("jLabel2");

        btnTribute.setText("jButton1");
        btnTribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTributeActionPerformed(evt);
            }
        });

        btnFinish.setText("jButton2");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        comboList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboListItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdbHU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                        .addComponent(rdbEN))
                    .addComponent(jScrollPane1)
                    .addComponent(lblCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFinish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCombo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155)
                .addComponent(btnTribute)
                .addGap(18, 18, 18)
                .addComponent(btnFinish)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbHU)
                    .addComponent(rdbEN))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdbHUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbHUActionPerformed
        locale = new Locale("hu", "HU");
        rdbHU.setFont(rdbHU.getFont().deriveFont(Font.BOLD));
        rdbEN.setFont(rdbEN.getFont().deriveFont(Font.PLAIN));
        useLanguage();
    }//GEN-LAST:event_rdbHUActionPerformed

    private void rdbENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbENActionPerformed
        locale = new Locale("en", "GB");
        rdbHU.setFont(rdbHU.getFont().deriveFont(Font.PLAIN));
        rdbEN.setFont(rdbEN.getFont().deriveFont(Font.BOLD));
        useLanguage();
    }//GEN-LAST:event_rdbENActionPerformed

    private void btnTributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTributeActionPerformed
        c.finishMainProcess();
    }//GEN-LAST:event_btnTributeActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        c.startFinale();
    }//GEN-LAST:event_btnFinishActionPerformed

    private void comboListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboListItemStateChanged
        String item = (String) evt.getItem();
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            System.out.println("comboSelected: " + comboSelected);
            System.out.println("item: " + item);
            if (!item.equals(comboSelected)) {
                c.startMainProcess(item);
            }
        }
        comboSelected = item;
    }//GEN-LAST:event_comboListItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnTribute;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCombo;
    private javax.swing.JLabel lblList;
    private javax.swing.JList<Sprite> lstHome;
    private javax.swing.JRadioButton rdbEN;
    private javax.swing.JRadioButton rdbHU;
    // End of variables declaration//GEN-END:variables
}
