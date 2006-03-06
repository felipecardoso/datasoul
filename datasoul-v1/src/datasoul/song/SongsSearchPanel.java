/*
 * SongsSearchPanel.java
 *
 * Created on 22 de Dezembro de 2005, 21:32
 */

package datasoul.song;

import datasoul.*;
import datasoul.util.*;
import datasoul.datashow.*;
import datasoul.song.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicDirectoryModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author  Administrador
 */
public class SongsSearchPanel extends javax.swing.JPanel implements javax.swing.event.TableModelListener{

    private Object objectManager;    
    private AllSongsListTable allSongsListTable;
    /**
     * Creates new form SongsSearchPanel
     */
    public SongsSearchPanel() {
        initComponents();

        tableSongList.setDroppable(false);
        
        comboField.removeAllItems();
        comboField.addItem("All");
        comboField.addItem("FileName");
        comboField.addItem("SongName");
        comboField.addItem("SongAuthor");
        comboField.addItem("Lyrics");
        comboField.setSelectedIndex(0);
        
        allSongsListTable = AllSongsListTable.getInstance();
        
        tableSongList.setModel(allSongsListTable);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        dnDTable1 = new datasoul.util.DnDTable();
        fieldString = new javax.swing.JTextField();
        comboField = new javax.swing.JComboBox();
        labelString = new javax.swing.JLabel();
        labelField = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        btnAddToList = new javax.swing.JButton();
        scroolSongList = new javax.swing.JScrollPane();
        tableSongList = new datasoul.util.DnDTable();

        dnDTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(dnDTable1);

        fieldString.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldStringKeyPressed(evt);
            }
        });

        comboField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelString.setText(java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("STRING"));

        labelField.setText(java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("FIELD"));

        toolBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddToList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datasoul/icons/addToList.gif")));
        btnAddToList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddToListMouseClicked(evt);
            }
        });

        toolBar.add(btnAddToList);

        tableSongList.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSongList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableSongListKeyPressed(evt);
            }
        });
        tableSongList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSongListMouseClicked(evt);
            }
        });

        scroolSongList.setViewportView(tableSongList);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelString)
                    .add(labelField))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(fieldString, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .add(comboField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap())
            .add(scroolSongList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
            .add(toolBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelString)
                    .add(fieldString, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelField)
                    .add(comboField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scroolSongList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(toolBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddToListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddToListMouseClicked
        if(objectManager instanceof DatashowPanel){
            DatashowPanel om = (DatashowPanel)objectManager;
            om.getServiceListPanel().addItem(tableSongList.getModel().getValueAt(tableSongList.getSelectedRow(),0));
        }else{
            SongsPanel om = (SongsPanel)objectManager;            
            om.getPraiseListPanel().addItem(tableSongList.getModel().getValueAt(tableSongList.getSelectedRow(),0));
        }
    }//GEN-LAST:event_btnAddToListMouseClicked

    private void tableSongListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSongListMouseClicked
        showItem();
    }//GEN-LAST:event_tableSongListMouseClicked

    private void tableSongListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableSongListKeyPressed
        showItem();
    }//GEN-LAST:event_tableSongListKeyPressed

    private void showItem(){
        if(objectManager instanceof DatashowPanel){
            DatashowPanel om = (DatashowPanel)objectManager;
            om.getPreviewPanel().previewItem((ServiceItem)tableSongList.getModel().getValueAt(tableSongList.getSelectedRow(),0));
        }else{
            SongsPanel om = (SongsPanel)objectManager;            
            om.getSongViewerPanel().viewSong((Song)tableSongList.getModel().getValueAt(tableSongList.getSelectedRow(),0));
        }
    }    
    
    private void fieldStringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldStringKeyPressed
        
        SongListTable foundSongTable = new SongListTable();
        
        for(int i=0; i<allSongsListTable.getRowCount();i++){
            try {
                String searchStr;
                if(String.valueOf(evt.getKeyChar()).equals("\b")){
                    searchStr = fieldString.getText().substring(0,fieldString.getText().length()-1);
                }else{
                    searchStr = fieldString.getText()+evt.getKeyChar();
                }
                if(comboField.getSelectedItem().toString().equals("All")){
                    for(int j=1; j<comboField.getItemCount();j++){
                        if(((Song)allSongsListTable.getValueAt(i,0)).containsStringInField(comboField.getItemAt(j).toString(),searchStr)){
                            foundSongTable.addItem(allSongsListTable.getValueAt(i,0));
                            break;
                        }                        
                    }
                }else if(((Song)allSongsListTable.getValueAt(i,0)).containsStringInField(comboField.getSelectedItem().toString(),searchStr)){
                    foundSongTable.addItem(allSongsListTable.getValueAt(i,0));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Error in searching.\nErro:"+ex.getMessage(),"DataSoul Error",0);    
            }
        }
        foundSongTable.addTableModelListener(this);        

        tableSongList.setModel(foundSongTable);

    }//GEN-LAST:event_fieldStringKeyPressed

    public void tableChanged(TableModelEvent e) {
        this.repaint();
    }

    public Object getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(Object objectManager) {
        this.objectManager = objectManager;
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToList;
    private javax.swing.JComboBox comboField;
    private datasoul.util.DnDTable dnDTable1;
    private javax.swing.JTextField fieldString;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelField;
    private javax.swing.JLabel labelString;
    private javax.swing.JScrollPane scroolSongList;
    private datasoul.util.DnDTable tableSongList;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
    
}
