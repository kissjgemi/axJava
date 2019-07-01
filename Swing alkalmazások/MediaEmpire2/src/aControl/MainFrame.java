/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class MainFrame extends javax.swing.JFrame {

    private final int FRAME_WIDTH = 618;
    private final int FRAME_HEIGHT = 650;
    private final String FRAME_TITLE = "Age Of Media";

    private Control c;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        super.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        super.setTitle(FRAME_TITLE);
        super.setLocationRelativeTo(null);
    }

    private void start() {
        this.setVisible(true);
        c = new Control(media3panel1, libraryPanel1, summaryPanel1, this);
        media3panel1.setControl(c);
        libraryPanel1.setControl(c);
        summaryPanel1.setControl(c);
        c.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chboxSort = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        libraryPanel1 = new aSurface.LibraryPanel();
        summaryPanel1 = new aSurface.SummaryPanel();
        media3panel1 = new aSurface.Media3Panel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuDataInput = new javax.swing.JMenu();
        menuDBInput = new javax.swing.JMenuItem();
        menuFileInput = new javax.swing.JMenu();
        menuInputFixFile = new javax.swing.JMenuItem();
        menuBrowsinFiles = new javax.swing.JMenuItem();
        menuSortBy = new javax.swing.JMenu();
        radioAbc = new javax.swing.JRadioButtonMenuItem();
        radioDate = new javax.swing.JRadioButtonMenuItem();
        radioArticles = new javax.swing.JRadioButtonMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        chkButtonAscending = new javax.swing.JCheckBoxMenuItem();
        menuHowTo = new javax.swing.JMenu();
        menuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(616, 650));

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(600, 520));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(618, 568));

        libraryPanel1.setMaximumSize(new java.awt.Dimension(600, 500));
        libraryPanel1.setMinimumSize(new java.awt.Dimension(600, 500));
        jTabbedPane1.addTab("újságok", libraryPanel1);
        jTabbedPane1.addTab("kimutatás", summaryPanel1);
        jTabbedPane1.addTab("MediaEmpire3", media3panel1);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        menuFile.setText("File");

        menuDataInput.setText("adatbevitel");

        menuDBInput.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuDBInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aControl/database_icon.jpg"))); // NOI18N
        menuDBInput.setText("Adatbázisból");
        menuDBInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDBInputActionPerformed(evt);
            }
        });
        menuDataInput.add(menuDBInput);

        menuFileInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aControl/file_icon.jpg"))); // NOI18N
        menuFileInput.setText("Fájlból");

        menuInputFixFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuInputFixFile.setText("Fix helyről");
        menuInputFixFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInputFixFileActionPerformed(evt);
            }
        });
        menuFileInput.add(menuInputFixFile);

        menuBrowsinFiles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        menuBrowsinFiles.setText("Választott fájl");
        menuBrowsinFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBrowsinFilesActionPerformed(evt);
            }
        });
        menuFileInput.add(menuBrowsinFiles);

        menuDataInput.add(menuFileInput);

        menuFile.add(menuDataInput);

        jMenuBar1.add(menuFile);

        menuSortBy.setText("Rendezés");

        chboxSort.add(radioAbc);
        radioAbc.setText("Névsorba");
        radioAbc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAbcActionPerformed(evt);
            }
        });
        menuSortBy.add(radioAbc);

        chboxSort.add(radioDate);
        radioDate.setText("Megjelenés szerint");
        radioDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDateActionPerformed(evt);
            }
        });
        menuSortBy.add(radioDate);

        chboxSort.add(radioArticles);
        radioArticles.setText("Cikkek száma szerint");
        radioArticles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioArticlesActionPerformed(evt);
            }
        });
        menuSortBy.add(radioArticles);
        menuSortBy.add(jSeparator1);

        chkButtonAscending.setSelected(true);
        chkButtonAscending.setText("növekvő sorrendben");
        chkButtonAscending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkButtonAscendingActionPerformed(evt);
            }
        });
        menuSortBy.add(chkButtonAscending);

        jMenuBar1.add(menuSortBy);

        menuHowTo.setText("Súgó");

        menuAbout.setText("Névjegy");
        menuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAboutActionPerformed(evt);
            }
        });
        menuHowTo.add(menuAbout);

        jMenuBar1.add(menuHowTo);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAboutActionPerformed
        JOptionPane.showMessageDialog(this,
                "Age of Media V0.9");
    }//GEN-LAST:event_menuAboutActionPerformed

    private void menuDBInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDBInputActionPerformed
        c.inputFromDB();
        c.listAllNews();
    }//GEN-LAST:event_menuDBInputActionPerformed

    private void menuInputFixFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInputFixFileActionPerformed
        c.inputFromFile();
        c.listAllNews();
    }//GEN-LAST:event_menuInputFixFileActionPerformed

    private void menuBrowsinFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBrowsinFilesActionPerformed
        c.inputFromSelectedFile();
        c.listAllNews();
    }//GEN-LAST:event_menuBrowsinFilesActionPerformed

    private void radioAbcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAbcActionPerformed
        c.sortByAbc(chkButtonAscending.isSelected());
    }//GEN-LAST:event_radioAbcActionPerformed

    private void radioDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDateActionPerformed
        c.sortByDate(chkButtonAscending.isSelected());
    }//GEN-LAST:event_radioDateActionPerformed

    private void radioArticlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioArticlesActionPerformed
        c.sortByArticleNumber(chkButtonAscending.isSelected());
    }//GEN-LAST:event_radioArticlesActionPerformed

    private void chkButtonAscendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkButtonAscendingActionPerformed
        if (radioAbc.isSelected()) {
            c.sortByAbc(chkButtonAscending.isSelected());
        }
        if (radioDate.isSelected()) {
            c.sortByDate(chkButtonAscending.isSelected());
        }
        if (radioArticles.isSelected()) {
            c.sortByArticleNumber(chkButtonAscending.isSelected());
        }
    }//GEN-LAST:event_chkButtonAscendingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup chboxSort;
    private javax.swing.JCheckBoxMenuItem chkButtonAscending;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private aSurface.LibraryPanel libraryPanel1;
    private aSurface.Media3Panel media3panel1;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuBrowsinFiles;
    private javax.swing.JMenuItem menuDBInput;
    private javax.swing.JMenu menuDataInput;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuFileInput;
    private javax.swing.JMenu menuHowTo;
    private javax.swing.JMenuItem menuInputFixFile;
    private javax.swing.JMenu menuSortBy;
    private javax.swing.JRadioButtonMenuItem radioAbc;
    private javax.swing.JRadioButtonMenuItem radioArticles;
    private javax.swing.JRadioButtonMenuItem radioDate;
    private aSurface.SummaryPanel summaryPanel1;
    // End of variables declaration//GEN-END:variables
}
