/*
 * PreviewPanel.java
 *
 * Created on 26 de Dezembro de 2005, 23:21
 */

package datasoul.datashow;

import datasoul.render.ContentManager;

/**
 *
 * @author  Administrador
 */
public class PreviewPanel extends javax.swing.JPanel {

    private DatashowPanel objectManager;    
    /**
     * Creates new form PreviewPanel
     */
    public PreviewPanel() {
        initComponents();
    }

    public DatashowPanel getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(DatashowPanel objectManager) {
        this.objectManager = objectManager;
    }

    public void previewItem(ServiceItem serviceItem){
        this.serviceItemTable1.setServiceItem(serviceItem);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        btnGoLive = new javax.swing.JButton();
        labelPreview = new javax.swing.JLabel();
        serviceItemTable1 = new datasoul.datashow.ServiceItemTable();

        btnGoLive.setText(java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("GO_LIVE"));
        btnGoLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoLiveActionPerformed(evt);
            }
        });

        labelPreview.setFont(new java.awt.Font("Arial", 3, 11));
        labelPreview.setText(java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("PREVIEW"));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(labelPreview)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 292, Short.MAX_VALUE)
                .add(btnGoLive)
                .addContainerGap())
            .add(serviceItemTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelPreview)
                    .add(btnGoLive))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceItemTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoLiveActionPerformed
        ServiceItem previewItem = this.getObjectManager().getPreviewPanel().serviceItemTable1.getServiceItem();
        
        this.getObjectManager().getLivePanel().showItem(previewItem);
        
        ContentManager cm = ContentManager.getInstance();
        cm.setTemplateLive( previewItem.getTemplate() );
        cm.setTitleLive( previewItem.getTitle() );
        cm.setSlideLive("");
        cm.setNextSlideLive("");
        cm.updateLive();
        
    }//GEN-LAST:event_btnGoLiveActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoLive;
    private javax.swing.JLabel labelPreview;
    private datasoul.datashow.ServiceItemTable serviceItemTable1;
    // End of variables declaration//GEN-END:variables
    
}
