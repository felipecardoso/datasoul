/*
 * ServiceItemTable.java
 *
 * Created on February 10, 2006, 11:22 PM
 */

package datasoul.datashow;

import java.util.Hashtable;
import javax.swing.JLabel;

/**
 *
 * @author  samuelm
 */
public class ServiceItemTable extends javax.swing.JPanel {
    
    ServiceItem item;
    
    /** Creates new form ServiceItemTable */
    public ServiceItemTable() {
        initComponents();
        Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put( 1, new JLabel("Small") );
        labels.put( 5, new JLabel("Large") );
        jSliderZoom.setLabelTable(labels);

        ServiceItem empty = new ServiceItem();
        setServiceItem(empty);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        displayTable = new javax.swing.JTable();
        jSliderZoom = new javax.swing.JSlider();

        jScrollPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane1ComponentResized(evt);
            }
        });

        displayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(displayTable);

        jSliderZoom.setMaximum(5);
        jSliderZoom.setMinimum(1);
        jSliderZoom.setPaintLabels(true);
        jSliderZoom.setSnapToTicks(true);
        jSliderZoom.setValue(3);
        jSliderZoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderZoomStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jSliderZoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jSliderZoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSliderZoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderZoomStateChanged
        int newSize = jSliderZoom.getValue();
        if (item != null){
            item.setZoom( jSliderZoom.getValue() );
            item.updateHeights(displayTable);
        }
        
    }//GEN-LAST:event_jSliderZoomStateChanged

    private void jScrollPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane1ComponentResized
        if (item != null){
            item.updateHeights(displayTable);
        }
        
    }//GEN-LAST:event_jScrollPane1ComponentResized
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable displayTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSliderZoom;
    // End of variables declaration//GEN-END:variables
    
    public void setServiceItem(ServiceItem item){
        this.item = item;
        item.registerJTable(displayTable);
        item.setZoom( jSliderZoom.getValue() );
        item.updateHeights(displayTable);        
        
    }

    public ServiceItem getServiceItem(){
        return this.item;        
    }
    
}
