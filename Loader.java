package pkg365assignnment2;
//Brian Dorsey 2016

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loader extends javax.swing.JFrame {

    //static Set<dataSource> sourceSet = new HashSet<>();
    List<dataSource> sourceList = new ArrayList<>();

    public Loader() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pgbLoad = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        pgbLoad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                pgbLoadPropertyChange(evt);
            }
        });

        jLabel1.setText("Loading...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pgbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pgbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pgbLoadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_pgbLoadPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_pgbLoadPropertyChange

    public List getSet() {
        return sourceList;
    }

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
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loader().setVisible(true);

            }
        });
    }

    public void changeVal(int val) {
        pgbLoad.setValue(val);
    }

    public void firstLoad() {//Loads each source URL data at launch to reduce search times.

        try {
            System.out.print("Loading: https://en.wikipedia.org/wiki/Computer");
            dataSource source0 = new dataSource("https://en.wikipedia.org/wiki/Computer", "Computers");
            source0.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(5);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Sport");
            dataSource source1 = new dataSource("https://en.wikipedia.org/wiki/Sport", "Sports");
            source1.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(10);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Television_channel");
            dataSource source2 = new dataSource("https://en.wikipedia.org/wiki/Television_channel", "Television");
            source2.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(15);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Video_game_console");
            dataSource source3 = new dataSource("https://en.wikipedia.org/wiki/Video_game_console", "Video Games");
            source3.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(20);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Food");
            dataSource source4 = new dataSource("https://en.wikipedia.org/wiki/Food", "Food");
            source4.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(25);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Film");
            dataSource source5 = new dataSource("https://en.wikipedia.org/wiki/Film", "Films");
            source5.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(30);
            
            System.out.print("Loading: https://en.wikipedia.org/wiki/World%27s_largest_cities");
            dataSource source6 = new dataSource("https://en.wikipedia.org/wiki/World%27s_largest_cities", "Cities");
            source6.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(35);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Transport");
            dataSource source7 = new dataSource("https://en.wikipedia.org/wiki/Transport", "Transportation");
            source7.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(40);
            
            System.out.print("Loading: https://en.wikipedia.org/wiki/History_of_the_United_States");
            dataSource source8 = new dataSource("https://en.wikipedia.org/wiki/History_of_the_United_States", "US History");
            source8.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(45);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Government");
            dataSource source9 = new dataSource("https://en.wikipedia.org/wiki/Government", "Government");
            source9.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(50);
            
            
            
            System.out.print("Loading: https://en.wikipedia.org/wiki/Electronics");
            dataSource source10 = new dataSource("https://en.wikipedia.org/wiki/Electronics", "Electronics");
            source10.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(55);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Car");
            dataSource source11 = new dataSource("https://en.wikipedia.org/wiki/Car", "Cars");
            source11.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(60);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Music");
            dataSource source12 = new dataSource("https://en.wikipedia.org/wiki/Music", "Music");
            source12.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(65);

            System.out.print("Loading: https://en.wikipedia.org/wiki/History_of_Europe");
            dataSource source13 = new dataSource("https://en.wikipedia.org/wiki/History_of_Europe", "History of Europe");
            source13.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(70);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Art");
            dataSource source14 = new dataSource("https://en.wikipedia.org/wiki/Art", "Art");
            source14.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(75);

            System.out.print("Loading: https://en.wikipedia.org/wiki/History_of_Africa");
            dataSource source15 = new dataSource("https://en.wikipedia.org/wiki/History_of_Africa", "History of Africa");
            source15.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(80);
            
            System.out.print("Loading: https://en.wikipedia.org/wiki/History_of_Asia");
            dataSource source16 = new dataSource("https://en.wikipedia.org/wiki/History_of_Asia", "History of Asia");
            source16.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(85);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Education");
            dataSource source17 = new dataSource("https://en.wikipedia.org/wiki/Education", "Education");
            source17.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(90);
            
            System.out.print("Loading: https://en.wikipedia.org/wiki/Category:Online_retail_companies_of_the_United_States");
            dataSource source18 = new dataSource("https://en.wikipedia.org/wiki/Category:Online_retail_companies_of_the_United_States", "Websites");
            source18.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(95);

            System.out.print("Loading: https://en.wikipedia.org/wiki/Language");
            dataSource source19 = new dataSource("https://en.wikipedia.org/wiki/Language", "Language");
            source19.writeTreetoFile("/Users/admin/Desktop/TEMP/");
            changeVal(100);
            
            
            

            sourceList.add(source0);
            sourceList.add(source1);
            sourceList.add(source2);
            sourceList.add(source3);
            sourceList.add(source4);
            sourceList.add(source5);
            sourceList.add(source6);
            sourceList.add(source7);
            sourceList.add(source8);
            sourceList.add(source9);

            sourceList.add(source10);
            sourceList.add(source11);
            sourceList.add(source12);
            sourceList.add(source13);
            sourceList.add(source14);
            sourceList.add(source15);
            sourceList.add(source16);
            sourceList.add(source17);
            sourceList.add(source18);
            sourceList.add(source19);
            
            
            System.out.println("Done.");

        } catch (Exception ex) {
            Logger.getLogger(compareUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar pgbLoad;
    // End of variables declaration//GEN-END:variables
}
