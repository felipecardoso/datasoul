/* 
 * Copyright 2005-2008 Samuel Mello & Eduardo Schnell
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; version 2 of the License.
 * 
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 * 
 */

/*
 * DatashowPanel.java
 *
 * Created on 26 de Dezembro de 2005, 23:03
 */

package datasoul.datashow;

import datasoul.config.BackgroundConfig;
import datasoul.config.ConfigObj;
import datasoul.config.WindowPropConfig;
import datasoul.render.ContentManager;
import datasoul.render.SwingPanelContentRender;
import datasoul.templates.DisplayTemplate;
import datasoul.util.ObjectManager;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author  Administrador
 */
public class DatashowPanel extends javax.swing.JPanel {

    private boolean updateSize;

    /**
     * Creates new form DatashowPanel
     */
    public DatashowPanel() {
        initComponents();
        
        ObjectManager.getInstance().setAuxiliarPanel(auxiliar);
        ObjectManager.getInstance().setServiceListPanel(serviceList);
        ObjectManager.getInstance().setPreviewPanel(preview);
        ObjectManager.getInstance().setLivePanel(live);
        
        
        java.util.ResourceBundle intl = java.util.ResourceBundle.getBundle("datasoul/internationalize");

        cbPreviewSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { 
            intl.getString("Dont_show"), 
            intl.getString("Small"), 
            intl.getString("Medium"), 
            intl.getString("Large") }));   
        cbPreviewSize.setSelectedIndex(1);
        
                
        cbLiveSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { 
            intl.getString("Dont_show"), 
            intl.getString("Small"), 
            intl.getString("Medium"), 
            intl.getString("Large") }));        
        cbLiveSize.setSelectedIndex(1);
            
        initPreview();
        initLive();

        WindowPropConfig.getInstance().getDatashowSplit1(jSplitPane1);
        WindowPropConfig.getInstance().getDatashowSplit2(jSplitPane2);
        WindowPropConfig.getInstance().getDatashowSplit3(jSplitPane3);
        updateSize = true;

    }

    private void initPreview(){
        int width, height;
        try{
            width = Integer.parseInt(ConfigObj.getInstance().getMonitorOutputSizeWidth());
        }catch(Exception e){
            width = DisplayTemplate.TEMPLATE_WIDTH;
        }
        try{
            height = Integer.parseInt(ConfigObj.getInstance().getMonitorOutputSizeHeight());
        }catch(Exception e){
            height = DisplayTemplate.TEMPLATE_HEIGHT;
        }
        
        SwingPanelContentRender contentRender = new SwingPanelContentRender(previewDisplayPanel1);
        contentRender.initDisplay( width, height, 0, 0 );
        
        ContentManager.getInstance().registerPreviewPanel( contentRender );
        
    }
    
    private void initLive(){
        int width, height;
        try{
            width = Integer.parseInt(ConfigObj.getInstance().getMainOutputSizeWidth());
        }catch(Exception e){
            width = 640;
        }
        try{
            height = Integer.parseInt(ConfigObj.getInstance().getMainOutputSizeHeight());
        }catch(Exception e){
            height = 480;
        }
        
        SwingPanelContentRender contentRender = new SwingPanelContentRender(liveDisplayPanel);
        contentRender.initDisplay( width, height, 0, 0 );
        
        ContentManager.getInstance().registerMainLiveRender( contentRender );
        contentRender.paintBackground(BackgroundConfig.getInstance().getMainBackgroundImg());


        if (ConfigObj.getInstance().getMonitorOutput()){
            SwingPanelContentRender contentRenderMon = new SwingPanelContentRender(monitorDisplayPanel);
            contentRenderMon.initDisplay( width, height, 0, 0 );

            ContentManager.getInstance().registerMonitorLiveRender( contentRenderMon );
            contentRenderMon.paintBackground(BackgroundConfig.getInstance().getMonitorBackgroundImg());
            String tmp = ContentManager.getMonitorDisplay().getTemplate();
            if (tmp != null){
                contentRenderMon.setTemplate(  tmp  );
            }
        }else{
            btnShowMonitor.setSelected(false);
            btnShowMonitor.setVisible(false);
            monitorDisplayPanel.setVisible(false);
        }
    
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        pnlPreview = new javax.swing.JPanel();
        pnlPreviewBox = new javax.swing.JPanel();
        previewDisplayPanel1 = new datasoul.render.SwingDisplayPanel();
        cbPreviewSize = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        preview = new datasoul.datashow.PreviewPanel();
        pnlLive = new javax.swing.JPanel();
        live = new datasoul.datashow.LivePanel();
        pnlLiveBox = new javax.swing.JPanel();
        cbLiveSize = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        liveDisplayPanel = new datasoul.render.SwingDisplayPanel();
        monitorDisplayPanel = new datasoul.render.SwingDisplayPanel();
        btnShowMonitor = new javax.swing.JToggleButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        serviceList = new datasoul.datashow.ServiceListPanel();
        auxiliar = new datasoul.datashow.AuxiliarPanel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jSplitPane2.setDividerLocation(320);
        jSplitPane2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jSplitPane2ComponentResized(evt);
            }
        });
        jSplitPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane2PropertyChange(evt);
            }
        });
        jSplitPane2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jSplitPane2AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jSplitPane3.setDividerLocation(300);
        jSplitPane3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane3PropertyChange(evt);
            }
        });

        previewDisplayPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        previewDisplayPanel1.setPreferredSize(new java.awt.Dimension(160, 120));
        previewDisplayPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                previewDisplayPanel1previewDisplayResized(evt);
            }
        });

        javax.swing.GroupLayout previewDisplayPanel1Layout = new javax.swing.GroupLayout(previewDisplayPanel1);
        previewDisplayPanel1.setLayout(previewDisplayPanel1Layout);
        previewDisplayPanel1Layout.setHorizontalGroup(
            previewDisplayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        previewDisplayPanel1Layout.setVerticalGroup(
            previewDisplayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        cbPreviewSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Don't show", "Small", "Medium", "Large" }));
        cbPreviewSize.setSelectedIndex(1);
        cbPreviewSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPreviewSizeActionPerformed(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("datasoul/internationalize"); // NOI18N
        jLabel10.setText(bundle.getString("Preview_Size")); // NOI18N

        javax.swing.GroupLayout pnlPreviewBoxLayout = new javax.swing.GroupLayout(pnlPreviewBox);
        pnlPreviewBox.setLayout(pnlPreviewBoxLayout);
        pnlPreviewBoxLayout.setHorizontalGroup(
            pnlPreviewBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPreviewBoxLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlPreviewBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previewDisplayPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPreviewBoxLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPreviewSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        pnlPreviewBoxLayout.setVerticalGroup(
            pnlPreviewBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPreviewBoxLayout.createSequentialGroup()
                .addGroup(pnlPreviewBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPreviewSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(previewDisplayPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        preview.setMinimumSize(new java.awt.Dimension(10, 10));

        javax.swing.GroupLayout pnlPreviewLayout = new javax.swing.GroupLayout(pnlPreview);
        pnlPreview.setLayout(pnlPreviewLayout);
        pnlPreviewLayout.setHorizontalGroup(
            pnlPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPreviewBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        pnlPreviewLayout.setVerticalGroup(
            pnlPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPreviewLayout.createSequentialGroup()
                .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPreviewBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane3.setTopComponent(pnlPreview);

        cbLiveSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Don't show", "Small", "Medium", "Large" }));
        cbLiveSize.setSelectedIndex(1);
        cbLiveSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLiveSizeActionPerformed(evt);
            }
        });

        jLabel11.setText(bundle.getString("Preview_Size")); // NOI18N

        liveDisplayPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        liveDisplayPanel.setPreferredSize(new java.awt.Dimension(160, 120));
        liveDisplayPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                liveDisplayPanelpreviewDisplayResized(evt);
            }
        });

        javax.swing.GroupLayout liveDisplayPanelLayout = new javax.swing.GroupLayout(liveDisplayPanel);
        liveDisplayPanel.setLayout(liveDisplayPanelLayout);
        liveDisplayPanelLayout.setHorizontalGroup(
            liveDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        liveDisplayPanelLayout.setVerticalGroup(
            liveDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        monitorDisplayPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        monitorDisplayPanel.setPreferredSize(new java.awt.Dimension(160, 120));
        monitorDisplayPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                monitorDisplayPanelpreviewDisplayResized(evt);
            }
        });

        javax.swing.GroupLayout monitorDisplayPanelLayout = new javax.swing.GroupLayout(monitorDisplayPanel);
        monitorDisplayPanel.setLayout(monitorDisplayPanelLayout);
        monitorDisplayPanelLayout.setHorizontalGroup(
            monitorDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        monitorDisplayPanelLayout.setVerticalGroup(
            monitorDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        btnShowMonitor.setSelected(true);
        btnShowMonitor.setText(bundle.getString("Show_Monitor")); // NOI18N
        btnShowMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMonitorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLiveBoxLayout = new javax.swing.GroupLayout(pnlLiveBox);
        pnlLiveBox.setLayout(pnlLiveBoxLayout);
        pnlLiveBoxLayout.setHorizontalGroup(
            pnlLiveBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLiveBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLiveBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLiveBoxLayout.createSequentialGroup()
                        .addComponent(liveDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monitorDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLiveBoxLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLiveSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnShowMonitor)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlLiveBoxLayout.setVerticalGroup(
            pnlLiveBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLiveBoxLayout.createSequentialGroup()
                .addGroup(pnlLiveBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLiveSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnShowMonitor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLiveBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liveDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monitorDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlLiveLayout = new javax.swing.GroupLayout(pnlLive);
        pnlLive.setLayout(pnlLiveLayout);
        pnlLiveLayout.setHorizontalGroup(
            pnlLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLiveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLiveBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(live, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );
        pnlLiveLayout.setVerticalGroup(
            pnlLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLiveLayout.createSequentialGroup()
                .addComponent(live, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLiveBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane3.setRightComponent(pnlLive);

        jSplitPane2.setRightComponent(jSplitPane3);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSplitPane1PropertyChange(evt);
            }
        });
        jSplitPane1.setLeftComponent(serviceList);
        jSplitPane1.setRightComponent(auxiliar);

        jSplitPane2.setLeftComponent(jSplitPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
    }//GEN-LAST:event_formComponentResized

    private void cbPreviewSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPreviewSizeActionPerformed
    
        Dimension d;
        
        switch (cbPreviewSize.getSelectedIndex()){
            
            case 0:
                previewDisplayPanel1.setVisible(false);
                break;
                
            case 1:
                applySizePanel(previewDisplayPanel1, 160, 120);
                break;
            
            case 2:
                applySizePanel(previewDisplayPanel1, 240, 180);
                break;

            case 3:
                applySizePanel(previewDisplayPanel1, 360, 240);
                break;
            
        }
        
        previewDisplayPanel1.revalidate();
        pnlPreviewBox.revalidate();
        pnlPreview.revalidate();
        
        
    }//GEN-LAST:event_cbPreviewSizeActionPerformed

    private void previewDisplayPanel1previewDisplayResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_previewDisplayPanel1previewDisplayResized
        
        Dimension dim= previewDisplayPanel1.getSize();
        int height = dim.height;
        int width = (dim.height/3)*4 ;
        dim.setSize(width,height);
        previewDisplayPanel1.setSize(dim);
        
    }//GEN-LAST:event_previewDisplayPanel1previewDisplayResized

    private void cbLiveSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLiveSizeActionPerformed

        Dimension d;
        
        switch (cbLiveSize.getSelectedIndex()){
            
            case 0:
                liveDisplayPanel.setVisible(false);
                monitorDisplayPanel.setVisible(false);
                break;
                
            case 1:
                applySizePanel(liveDisplayPanel, 160, 120);
                if (btnShowMonitor.isSelected()){
                    applySizePanel(monitorDisplayPanel, 160, 120);
                }
                break;
            
            case 2:
                applySizePanel(liveDisplayPanel, 240, 180);
                if (btnShowMonitor.isSelected()){
                    applySizePanel(monitorDisplayPanel, 240, 180);
                }
                break;

            case 3:
                applySizePanel(liveDisplayPanel, 360, 240);
                if (btnShowMonitor.isSelected()){
                    applySizePanel(monitorDisplayPanel, 360, 240);
                }
                break;
            
        }
        
        liveDisplayPanel.revalidate();
        pnlLiveBox.revalidate();
        pnlLive.revalidate();
        
}//GEN-LAST:event_cbLiveSizeActionPerformed

    private void applySizePanel(JPanel p, int width, int height){
        p.setVisible(true);
        Dimension d = new Dimension(width, height);
        p.setSize(d);
        p.setPreferredSize(d);
        p.setMinimumSize(d);
        p.setMaximumSize(d);
    }
    
    private void liveDisplayPanelpreviewDisplayResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_liveDisplayPanelpreviewDisplayResized
        
        Dimension dim= liveDisplayPanel.getSize();
        int height = dim.height;
        int width = (dim.height/3)*4 ;
        dim.setSize(width,height);
        liveDisplayPanel.setSize(dim);

}//GEN-LAST:event_liveDisplayPanelpreviewDisplayResized

    private void monitorDisplayPanelpreviewDisplayResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_monitorDisplayPanelpreviewDisplayResized
        // TODO add your handling code here:
}//GEN-LAST:event_monitorDisplayPanelpreviewDisplayResized

    private void btnShowMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMonitorActionPerformed
        
        if (!btnShowMonitor.isSelected()){
            monitorDisplayPanel.setVisible(false);
        }else{
            cbLiveSizeActionPerformed(evt);
        }
}//GEN-LAST:event_btnShowMonitorActionPerformed

    private void jSplitPane2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSplitPane2ComponentResized
    }//GEN-LAST:event_jSplitPane2ComponentResized

    private void jSplitPane2AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSplitPane2AncestorMoved
    }//GEN-LAST:event_jSplitPane2AncestorMoved

    private void jSplitPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane2PropertyChange
        if (updateSize && evt.getPropertyName().equals(javax.swing.JSplitPane.DIVIDER_LOCATION_PROPERTY)){
                WindowPropConfig.getInstance().setDatashowSplit2(Integer.toString(jSplitPane2.getDividerLocation()));
        }
    }//GEN-LAST:event_jSplitPane2PropertyChange

    private void jSplitPane3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane3PropertyChange
        if (updateSize && evt.getPropertyName().equals(javax.swing.JSplitPane.DIVIDER_LOCATION_PROPERTY)){
                WindowPropConfig.getInstance().setDatashowSplit3(Integer.toString(jSplitPane3.getDividerLocation()));
        }
    }//GEN-LAST:event_jSplitPane3PropertyChange

    private void jSplitPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSplitPane1PropertyChange
        if (updateSize && evt.getPropertyName().equals(javax.swing.JSplitPane.DIVIDER_LOCATION_PROPERTY)){
                WindowPropConfig.getInstance().setDatashowSplit1(Integer.toString(jSplitPane1.getDividerLocation()));
        }
    }//GEN-LAST:event_jSplitPane1PropertyChange
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datasoul.datashow.AuxiliarPanel auxiliar;
    private javax.swing.JToggleButton btnShowMonitor;
    private javax.swing.JComboBox cbLiveSize;
    private javax.swing.JComboBox cbPreviewSize;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private datasoul.datashow.LivePanel live;
    private datasoul.render.SwingDisplayPanel liveDisplayPanel;
    private datasoul.render.SwingDisplayPanel monitorDisplayPanel;
    private javax.swing.JPanel pnlLive;
    private javax.swing.JPanel pnlLiveBox;
    private javax.swing.JPanel pnlPreview;
    private javax.swing.JPanel pnlPreviewBox;
    private datasoul.datashow.PreviewPanel preview;
    private datasoul.render.SwingDisplayPanel previewDisplayPanel1;
    private datasoul.datashow.ServiceListPanel serviceList;
    // End of variables declaration//GEN-END:variables
    
}
