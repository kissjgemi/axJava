package surface;

import basis.Gadget;
import basis.Human;
import control.Control;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author KissJGabi
 */
public class SimulationPanel extends javax.swing.JPanel {

    private DefaultListModel<Human> humasListModel
            = new DefaultListModel<>();
    private DefaultComboBoxModel<Gadget> gadgetListModel
            = new DefaultComboBoxModel<>();

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    public SimulationPanel() {
        initComponents();
        lstHumans.setModel(humasListModel);
        cmbxPhones.setModel(gadgetListModel);
    }

    public void showHumans(List<Human> humansList) {
        humasListModel.clear();
        humansList.forEach((human) -> {
            humasListModel.addElement(human);
        });

        lblDiagnosis.setText("");
    }

    public void showGadgets(List<Gadget> gadgetsLiast) {
        gadgetListModel.removeAllElements();
        gadgetsLiast.forEach((gadget) -> {
            gadgetListModel.addElement(gadget);
        });
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
        jLabel2 = new javax.swing.JLabel();
        cmbxPhones = new javax.swing.JComboBox<>();
        btnUsePhone = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        lblDiagnosis = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHumans = new javax.swing.JList<>();

        setMaximumSize(new java.awt.Dimension(600, 420));
        setMinimumSize(new java.awt.Dimension(600, 420));
        setPreferredSize(new java.awt.Dimension(600, 420));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Emberek");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Telefon választék");

        cmbxPhones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxPhonesActionPerformed(evt);
            }
        });

        btnUsePhone.setText("Használat");
        btnUsePhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsePhoneActionPerformed(evt);
            }
        });

        btnSort.setText("Rendez");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        lblDiagnosis.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblDiagnosis.setText("nincs káros függősége");

        lstHumans.setModel(new javax.swing.AbstractListModel<Human>() {
            Human[] humans;
            public int getSize() { return humans.length; }
            public Human getElementAt(int i) { return humans[i]; }
        });
        lstHumans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstHumansMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstHumans);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addGap(20, 20, 20)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbxPhones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUsePhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbxPhones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)
                        .addComponent(btnUsePhone))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSort)
                    .addComponent(lblDiagnosis))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxPhonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxPhonesActionPerformed
        Gadget gadget = (Gadget) cmbxPhones.getSelectedItem();
        if (gadget != null) {
            c.buyGadget(gadget);
        }
    }//GEN-LAST:event_cmbxPhonesActionPerformed

    private void btnUsePhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsePhoneActionPerformed
        c.usePhones();
    }//GEN-LAST:event_btnUsePhoneActionPerformed

    private void lstHumansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstHumansMouseClicked
        Human human = (Human) lstHumans.getSelectedValue();
        if (human != null) {
            lblDiagnosis.setText(human.diagnosis());
        }
    }//GEN-LAST:event_lstHumansMouseClicked

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        c.orderList();
    }//GEN-LAST:event_btnSortActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnUsePhone;
    private javax.swing.JComboBox<Gadget> cmbxPhones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiagnosis;
    private javax.swing.JList<Human> lstHumans;
    // End of variables declaration//GEN-END:variables
}
