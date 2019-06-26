/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aBasis.Article;
import aBasis.InetNews;
import aBasis.Messenger;
import aBasis.NewsPaper;
import aBasis.SortableListModel;
import aControl.Control;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author b6dmin
 */
public class LibraryPanel extends javax.swing.JPanel {

    private SortableListModel<Messenger> newsListModel
            = new SortableListModel<>();
    private DefaultListModel<Article> articleListModel
            = new DefaultListModel<>();
    private DefaultListModel<String> articleNumberMaxListModel
            = new DefaultListModel<>();

    private Control control;

    public void setControl(Control c) {
        this.control = c;
    }

    /**
     * Creates new form LibraryPanel
     */
    public LibraryPanel() {
        initComponents();
        lstMessengers.setModel(newsListModel);
        lstArticles.setModel(articleListModel);
        lstMostAppearances.setModel(articleNumberMaxListModel);
    }

    public void clearLibraryPanel() {
        articleListModel.clear();
        newsListModel.clear();
        articleNumberMaxListModel.clear();
        lstMessengers.clearSelection();
        lstArticles.clearSelection();
        lstMostAppearances.clearSelection();
        lblArticleNumber.setText(" Legnagyobb cikkszám:");
        lblAverageLie.setText(" Hazugság átlag:");
        lblInfo.setText(" Példányszm/Url:");
    }

    public void clearNewsArticles() {
        newsListModel.clear();
        articleListModel.clear();
        lstMessengers.clearSelection();
        lstArticles.clearSelection();
        lblAverageLie.setText(" Hazugság átlag:");
        lblInfo.setText(" Példányszm/Url:");
    }

    public void listMessengers(List<Messenger> list) {
        newsListModel.clear();
        switch (comboFilter.getSelectedIndex()) {
            case 1: {
                list.forEach((news) -> {
                    if (news.getDATE().substring(0, 7).equals(Messenger
                            .getActualDate().substring(0, 7))) {
                        newsListModel.addElement(news);
                    }
                });
                break;
            }
            case 2: {
                list.forEach((news) -> {
                    if (news.getDATE().substring(0, 4).equals(Messenger
                            .getActualDate().substring(0, 4))) {
                        newsListModel.addElement(news);
                    }
                });
                break;
            }
            case 3: {
                list.forEach((news) -> {
                    if (Integer.valueOf(news.getDATE().substring(0, 4))
                            == Integer.valueOf(Messenger.
                                    getActualDate().substring(0, 4)) - 1) {
                        newsListModel.addElement(news);
                    }
                });
                break;
            }
            default: {
                list.forEach((news) -> {
                    newsListModel.addElement(news);
                });
            }
        }
        if (newsListModel.isRendezett()) {
            sortingBy();
        }
        lstMessengers.repaint();
    }

    public void listGraetestMessengers(List<Messenger> list, int max) {
        lblArticleNumber.setText(" Legnagyobb cikkszám: (" + max + ")");
        articleNumberMaxListModel.clear();
        switch (comboFilter.getSelectedIndex()) {
            case 1: {
                list.forEach((news) -> {
                    if (news.getDATE().substring(0, 7).equals(Messenger
                            .getActualDate().substring(0, 7))) {
                        articleNumberMaxListModel.addElement(news.getNAME());
                    }
                });
                break;
            }
            case 2: {
                list.forEach((news) -> {
                    if (news.getDATE().substring(0, 4).equals(Messenger
                            .getActualDate().substring(0, 4))) {
                        articleNumberMaxListModel.addElement(news.getNAME());
                    }
                });
                break;
            }
            case 3: {
                list.forEach((news) -> {
                    if (Integer.valueOf(news.getDATE().substring(0, 4))
                            == Integer.valueOf(Messenger.
                                    getActualDate().substring(0, 4)) - 1) {
                        articleNumberMaxListModel.addElement(news.getNAME());
                    }
                });
                break;
            }
            default: {
                list.forEach((news) -> {
                    articleNumberMaxListModel.addElement(news.getNAME());
                });
            }
        }
        lstMostAppearances.repaint();
    }

    public void listArticles(List<Article> list) {
        articleListModel.clear();
        for (Article article : list) {
            articleListModel.addElement(article);
        }
        lstArticles.repaint();
    }

    public void sortingBy() {
        newsListModel.sort();
        newsListModel.setRendezett(true);
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstArticles = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstMessengers = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstMostAppearances = new javax.swing.JList<>();
        lblArticleNumber = new javax.swing.JLabel();
        btnSimulation = new javax.swing.JButton();
        comboFilter = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lblAverageLie = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MÉDIABIRODALOM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Újságok");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cikkek");

        lstArticles.setModel(new javax.swing.AbstractListModel<Article>() {
            Article[] a;
            public int getSize() { return a.length; }
            public Article getElementAt(int i) { return a[i]; }
        });
        lstArticles.setToolTipText("Ha kijelöl több cikket, akkor a lista alatt lehet látni a kijelölt cikkek hazugság átlagát");
        lstArticles.setPreferredSize(new java.awt.Dimension(270, 200));
        lstArticles.setVerifyInputWhenFocusTarget(false);
        lstArticles.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstArticlesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstArticles);

        lstMessengers.setModel(new javax.swing.AbstractListModel<Messenger>() {
            Messenger[] m;
            public int getSize() { return m.length; }
            public Messenger getElementAt(int i) { return m[i]; }
        });
        lstMessengers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstMessengers.setToolTipText("Kattintson a felületre az újságcikkek megjelenítéséhez!");
        lstMessengers.setPreferredSize(new java.awt.Dimension(270, 200));
        lstMessengers.setVerifyInputWhenFocusTarget(false);
        lstMessengers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstMessengersValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstMessengers);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(270, 260));

        lstMostAppearances.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstMostAppearances.setPreferredSize(new java.awt.Dimension(270, 80));
        lstMostAppearances.setVisibleRowCount(16);
        jScrollPane3.setViewportView(lstMostAppearances);

        lblArticleNumber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblArticleNumber.setText(" Legnagyobb cikkszám:");

        btnSimulation.setText("Újságírás");
        btnSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimulationActionPerformed(evt);
            }
        });

        comboFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mind", "E havi", "Idei", "Tavalyi" }));
        comboFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFilterItemStateChanged(evt);
            }
        });

        jLabel5.setText("Szűrés:");

        lblAverageLie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAverageLie.setText(" Hazugság átlag: ");

        lblInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInfo.setText(" Példányszm/Url:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArticleNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSimulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAverageLie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAverageLie)
                    .addComponent(lblInfo))
                .addGap(18, 18, 18)
                .addComponent(lblArticleNumber)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnSimulation))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimulationActionPerformed
        control.journalism_theTest();
    }//GEN-LAST:event_btnSimulationActionPerformed

    private void comboFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFilterItemStateChanged
        clearLibraryPanel();
        control.listAllNews();
    }//GEN-LAST:event_comboFilterItemStateChanged

    private void lstMessengersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstMessengersValueChanged
        Messenger m = lstMessengers.getSelectedValue();
        lblAverageLie.setText(" Hazugság átlag:");
        if (m != null) {
            String str;
            if (m instanceof InetNews) {
                str = "link: " + ((InetNews) m).getURL();
            } else {
                str = "példányszáma: " + ((NewsPaper) m).getNUM_OF_COPIES();
            }
            lblInfo.setText(str);
            if (!m.getArticles().isEmpty()) {
                listArticles(m.getArticles());
            }
        }
    }//GEN-LAST:event_lstMessengersValueChanged

    private void lstArticlesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstArticlesValueChanged
        List<Article> articles = lstArticles.getSelectedValuesList();
        int sum = 0;
        for (Object article : articles) {
            System.out.println("> " + article);
            lblAverageLie.setText(" Hazugság átlag: "
                    + control.sumOfLies(articles) + " %");
        }
    }//GEN-LAST:event_lstArticlesValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimulation;
    private javax.swing.JComboBox<String> comboFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblArticleNumber;
    private javax.swing.JLabel lblAverageLie;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JList<Article> lstArticles;
    private javax.swing.JList<Messenger> lstMessengers;
    private javax.swing.JList<String> lstMostAppearances;
    // End of variables declaration//GEN-END:variables
}
