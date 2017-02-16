package pkg365assignnment2;
//Brian Dorsey 2016

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class compareUI extends javax.swing.JFrame {

    //static Set<dataSource> sourceSet = new HashSet<>();
    List<dataSource> sourceList = new ArrayList<>();

    public compareUI(List dataSet) {
        sourceList = dataSet;
        initComponents();
        lblSearching.setText("Loading...");
        lblSearching.setText("Ready.");
        txaResults.setText("");
        

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        btnGo = new javax.swing.JButton();
        tbxSearch = new javax.swing.JTextField();
        lblSearching = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaResults = new javax.swing.JTextArea();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnGo.setText("Go");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        jLabel1.setText("Top Categories:");

        jLabel2.setText("URL to compare:");

        txaResults.setEditable(false);
        txaResults.setColumns(20);
        txaResults.setLineWrap(true);
        txaResults.setRows(5);
        jScrollPane1.setViewportView(txaResults);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblSearching, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSearching, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed

        txaResults.setMaximumSize(txaResults.getSize());
        txaResults.setMinimumSize(txaResults.getSize());
        
        tbxSearch.setMaximumSize(txaResults.getSize());
        tbxSearch.setMinimumSize(txaResults.getSize());
        
        lblSearching.setText("Searching...");
        txaResults.setText("");

        String searchTerm = tbxSearch.getText();

        System.out.print("Loading Source Data...");

        System.out.println("Searching... " + searchTerm);

        //System.out.println(element.compareSoure(searchTerm));
        for (dataSource temp : sourceList) {
            //System.out.println("l: "+temp.getURL());
            double score = 0.0;
            try {
                score = temp.compareSource(searchTerm);
                temp.lastScore = score;
            } catch (Exception ex) {
                Logger.getLogger(compareUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(score + ":\t" + temp.getURL());
        }
        System.out.println("\n");
        //resultsHelper[] bigList = new resultsHelper[10];
        int pos = 0;
        for (int x = 0; x < 5; x++) {
            double biggestScore = -1.0;
            String biggestURL = "";
            String biggestPage = "ERR";
            for (dataSource temp : sourceList) {
                if (temp.lastScore != -1.0) {
                    if (biggestScore < temp.lastScore) {
                        biggestScore = temp.lastScore;
                        biggestPage = temp.name;
                        biggestURL = temp.getURL();
                        temp.lastScore = -1.0;
                        pos++;
                    }
                }
            }
//            bigList[pos].page = biggestPage;
//            bigList[pos].score = biggestScore;
            //txaResults.setText(txaResults.getText() + biggestPage + ": " + biggestURL + "\n");
            txaResults.setText(txaResults.getText() + biggestPage + "\n");
            //System.out.println(biggestScore + "\t" + biggestPage);
            
        }
        System.out.println("Done.");
        lblSearching.setText("Done.");
    }//GEN-LAST:event_btnGoActionPerformed

    class resultsHelper {

        double score;
        String page;

        public resultsHelper(double s, String p) {
            score = s;
            page = p;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearching;
    private javax.swing.JTextField tbxSearch;
    private javax.swing.JTextArea txaResults;
    // End of variables declaration//GEN-END:variables
}
