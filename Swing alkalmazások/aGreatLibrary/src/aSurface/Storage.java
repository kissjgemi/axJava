/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.Book;
import aControl.Control;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author b6dmin
 */
public class Storage extends javax.swing.JPanel {

    private DefaultListModel bookListModel = new DefaultListModel();

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    /**
     * Creates new form Storage
     */
    public Storage() {
        initComponents();
        lstStorage.setModel(bookListModel);
    }

    public void listBook(List<Book> list) {
        bookListModel.clear();
        list.forEach((book) -> {
            bookListModel.addElement(book + " " + book.numberOfCopiesText());
            //System.out.println("- " + book);
        });
        Dimension preferredSize;
        preferredSize = new Dimension(516, list.size() * 20);
        lstStorage.setPreferredSize(preferredSize);
        validate();
    }

    public void fillDataFields(Book b) {
        txtAuthor.setText(b.getAuthor());
        txtTitle.setText(b.getTitle());
        txtIsbn.setText(b.getIsbn());
        txtPurchaseNr.setText("0");
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
        lstStorage = new javax.swing.JList<>();
        lblStorage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblIsbn = new javax.swing.JLabel();
        lblPurchaseNr = new javax.swing.JLabel();
        btnBuyBook = new javax.swing.JButton();
        txtAuthor = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        txtIsbn = new javax.swing.JTextField();
        txtPurchaseNr = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 204));

        lstStorage.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstStorage.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstStorage.setPreferredSize(new java.awt.Dimension(514, 300));
        lstStorage.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstStorageValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstStorage);

        lblStorage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblStorage.setText("Raktárkészlet:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Beszerzés:");

        lblAuthor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAuthor.setText("Szerző:");

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitle.setText("Cím:");

        lblIsbn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblIsbn.setText("Isbn:");

        lblPurchaseNr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPurchaseNr.setText("Példányszám:");

        btnBuyBook.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBuyBook.setText("Vásárlás");
        btnBuyBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyBookActionPerformed(evt);
            }
        });

        txtAuthor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAuthor.setText("Szerző");

        txtTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTitle.setText("Cím");

        txtIsbn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIsbn.setText("Isbn");

        txtPurchaseNr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPurchaseNr.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtPurchaseNr.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblStorage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPurchaseNr)
                                .addGap(18, 18, 18)
                                .addComponent(txtPurchaseNr, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                                .addComponent(btnBuyBook, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAuthor)
                                    .addComponent(lblTitle)
                                    .addComponent(lblIsbn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIsbn)
                                    .addComponent(txtTitle)
                                    .addComponent(txtAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblStorage)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAuthor)
                            .addComponent(txtAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitle)
                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIsbn)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPurchaseNr)
                            .addComponent(txtPurchaseNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuyBook))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuyBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyBookActionPerformed
        if (Integer.parseInt(txtPurchaseNr.getText()) != 0) {
            System.out.println("txtPurchaseNr: "
                    + Integer.parseInt(txtPurchaseNr.getText()));
            c.buyABook(txtAuthor.getText(),
                    txtTitle.getText(),
                    txtIsbn.getText(),
                    Integer.parseInt(txtPurchaseNr.getText()));
        }
    }//GEN-LAST:event_btnBuyBookActionPerformed

    private void lstStorageValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstStorageValueChanged
        if (lstStorage.getSelectedIndex() >= 0 && !evt.getValueIsAdjusting()) {
            int index = lstStorage.getSelectedIndex();
            c.fillPurchaseFields(index);
        }
    }//GEN-LAST:event_lstStorageValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuyBook;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblIsbn;
    private javax.swing.JLabel lblPurchaseNr;
    private javax.swing.JLabel lblStorage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<String> lstStorage;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtPurchaseNr;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
