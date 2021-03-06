/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.Book;
import aControl.Control;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author b6dmin
 */
public class BookList extends javax.swing.JPanel {

    private Image myImage
            = new ImageIcon(this.getClass().
                    getResource("/aSurface/konyv_vs_tv.jpg")).getImage();
    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    /**
     * Creates new form BookList
     */
    public BookList() {
        initComponents();
        setVisibility(false);
    }

    private void setVisibility(boolean b) {
        lblTitle.setVisible(b);
        pnlBackground.setVisible(b);
        btnBorrowBook.setVisible(b);
        btnBringBack.setVisible(b);
    }

    List<JCheckBox> chkBoxes = new ArrayList<>();
    int distance_v = 20;
    int gap = 40;

    public void listAllBooks(List<Book> bookList) {

        lstBookList.removeAll();
        chkBoxes.clear();

        JCheckBox chkBox;
        JLabel label;

        int distance_h = 10;

        int chkBoxWidth = 380;
        int chkBoxnHeight = 20;

        int labelDistance_h = 410;
        int labelWidth = 100;
        int labelHeight = 20;

        int scrollBarWidth = 21;

        for (int ii = 0; ii < bookList.size(); ii++) {
            chkBox = new JCheckBox(bookList.get(ii).toString());
            chkBox.setLocation(distance_h, distance_v + ii * gap);
            chkBox.setSize(chkBoxWidth, chkBoxnHeight);

            this.lstBookList.add(chkBox);
            chkBoxes.add(chkBox);

            label = new JLabel(bookList.get(ii).numberOfCopiesText());
            label.setLocation(labelDistance_h, distance_v + ii * gap);
            label.setSize(labelWidth, labelHeight);
            this.lstBookList.add(label);
        }
        Dimension preferredSize
                = new Dimension(lstBookList.getWidth() - scrollBarWidth,
                        bookList.size() * gap + distance_v);
        lstBookList.setPreferredSize(preferredSize);
        validate();
    }

    public void refresh(int index, String numOfCopiesString) {
        int labelIndex = 2 * index + 1;
        if (lstBookList.getComponents()[labelIndex] instanceof JLabel) {
            ((JLabel) lstBookList.getComponents()[labelIndex]).
                    setText(numOfCopiesString);
        }
    }

    public void setTextLocale(ResourceBundle bundle) {
        lblTitle.setText(bundle.getString("LBL_BOOKLIST_TXT"));
        btnDataInput.setText(bundle.getString("BTN_LOAD_TXT"));
        btnBorrowBook.setText(bundle.getString("BTN_LOAN_TXT"));
        btnBringBack.setText(bundle.getString("BTN_BACK_TXT"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlBackground = new javax.swing.JScrollPane();
        lstBookList = new javax.swing.JPanel();
        btnBringBack = new javax.swing.JButton();
        btnBorrowBook = new javax.swing.JButton();
        btnDataInput = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 153, 153));

        lblTitle.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Könyvlista");

        lstBookList.setBackground(new java.awt.Color(255, 204, 204));
        lstBookList.setPreferredSize(new java.awt.Dimension(500, 400));

        javax.swing.GroupLayout lstBookListLayout = new javax.swing.GroupLayout(lstBookList);
        lstBookList.setLayout(lstBookListLayout);
        lstBookListLayout.setHorizontalGroup(
            lstBookListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );
        lstBookListLayout.setVerticalGroup(
            lstBookListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        pnlBackground.setViewportView(lstBookList);

        btnBringBack.setText("Visszavétel");
        btnBringBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBringBackActionPerformed(evt);
            }
        });

        btnBorrowBook.setText("Kikölcsönzés");
        btnBorrowBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrowBookActionPerformed(evt);
            }
        });

        btnDataInput.setText("Adatbeolvasás");
        btnDataInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBackground)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBorrowBook, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBringBack, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(190, 190, 190)
                    .addComponent(btnDataInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrowBook)
                    .addComponent(btnBringBack))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(480, 480, 480)
                    .addComponent(btnDataInput)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDataInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataInputActionPerformed
        c.start();
        btnDataInput.setVisible(false);
        myImage = null;
        this.repaint();
        setVisibility(true);
    }//GEN-LAST:event_btnDataInputActionPerformed

    private void btnBorrowBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrowBookActionPerformed
        for (JCheckBox checkbox : chkBoxes) {
            if (checkbox.isSelected()) {
                System.out.println("> " + checkbox.getText());
                int index = (checkbox.getY() - distance_v) / gap;
                c.bookOut(index);
                checkbox.setSelected(false);
            }
}    }//GEN-LAST:event_btnBorrowBookActionPerformed

    private void btnBringBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBringBackActionPerformed
        for (JCheckBox checkbox : chkBoxes) {
            if (checkbox.isSelected()) {
                System.out.println("> " + checkbox.getText());
                int index = (checkbox.getY() - distance_v) / gap;
                c.bookIn(index);
                checkbox.setSelected(false);
            }
        }
    }//GEN-LAST:event_btnBringBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrowBook;
    private javax.swing.JButton btnBringBack;
    private javax.swing.JButton btnDataInput;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel lstBookList;
    private javax.swing.JScrollPane pnlBackground;
    // End of variables declaration//GEN-END:variables
}
