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
 * ImportServiceItemForm.java
 *
 * Created on 7 de Abril de 2006, 23:32
 */

package datasoul.datashow;

import datasoul.servicelist.ServiceListTable;
import datasoul.DatasoulMainForm;

/**
 *
 * @author  Administrador
 */
public class ImportServiceItemForm extends javax.swing.JFrame {

    private ServiceListTable serviceListTable;

    /** Creates new form ImportServiceItemForm */
    public ImportServiceItemForm() {
        initComponents();
        DatasoulMainForm.setDatasoulIcon(this);
        serviceListTable = new ServiceListTable();
        tableServiceList.setModel(serviceListTable);
        setColorRender();
    }
    
    private void setColorRender(){
        ServiceListColorRender cr = new ServiceListColorRender();

        for (int i=0; i < this.tableServiceList.getColumnModel().getColumnCount(); i++){
            this.tableServiceList.getColumnModel().getColumn(i).setCellRenderer(cr);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroolServiceList = new javax.swing.JScrollPane();
        tableServiceList = new datasoul.util.DnDTable();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("datasoul/internationalize"); // NOI18N
        setTitle(bundle.getString("Import_Service_Item")); // NOI18N

        tableServiceList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scroolServiceList.setViewportView(tableServiceList);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datasoul/icons/v2/window-close.png"))); // NOI18N
        btnClose.setText(bundle.getString("Close")); // NOI18N
        btnClose.setToolTipText(bundle.getString("Close_window")); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel1.setText(bundle.getString("Select_another_service_list_to_import_an_item")); // NOI18N

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datasoul/icons/v2/document-open.png"))); // NOI18N
        btnOpen.setText(bundle.getString("Open")); // NOI18N
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datasoul/icons/v2/stock_task-assigned.png"))); // NOI18N
        btnImport.setText(bundle.getString("Import")); // NOI18N
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroolServiceList, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroolServiceList, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(btnOpen)
                    .addComponent(btnImport))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed

        serviceListTable.openServiceList();

    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        for(int item:tableServiceList.getSelectedRows())
            ServiceListTable.getActiveInstance().addItem(serviceListTable.getServiceItem(item));
    }//GEN-LAST:event_btnImportActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scroolServiceList;
    private datasoul.util.DnDTable tableServiceList;
    // End of variables declaration//GEN-END:variables

}
