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
 * TemplateEditorPanel.java
 *
 * Created on December 25, 2005, 1:45 PM
 */

package datasoul.templates;

import datasoul.util.AttributedObject;
import datasoul.util.ShowDialog;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.InputEvent;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author  samuelm
 */
public class TemplateEditorPanel extends javax.swing.JPanel
        implements javax.swing.event.TableModelListener {
    
    private TemplateItem selectedItem = null;
    private Point dragBegin;
    private Rectangle dragOrigSize;
    private JTable propTable;
    private DisplayTemplate template;
    private JLabel activeItemLabel;
    private TemplateEditorFrame editorFrame;
    
    /** Creates new form TemplateEditorPanel */
    public TemplateEditorPanel() {
        initComponents();
        setMaximumSize(new java.awt.Dimension(DisplayTemplate.TEMPLATE_WIDTH, DisplayTemplate.TEMPLATE_HEIGHT));
        setMinimumSize(new java.awt.Dimension(DisplayTemplate.TEMPLATE_WIDTH, DisplayTemplate.TEMPLATE_HEIGHT));
        setPreferredSize(new java.awt.Dimension(DisplayTemplate.TEMPLATE_WIDTH, DisplayTemplate.TEMPLATE_HEIGHT));
        
    }
    
    public void addItem(TemplateItem t){
        
        template.addItem(t);
        t.addTableModelListener(this);
        selectedItem = t;
        this.repaint();
        
    }
    
    private Color gridColor = new Color(221, 221, 221);
    
    @Override
    public void paint(java.awt.Graphics g) {
        
        super.paint(g);
        
        Graphics2D g2 =  (Graphics2D) g;
        
        g2.setColor(gridColor);
        for (int i=0; i<this.getSize().width; i+=10){
            g2.drawLine(i, 0, i, this.getSize().height);
        }
        for (int i=0; i<this.getSize().height; i+=10){
            g2.drawLine(0, i, this.getSize().width, i);
        }
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, this.getSize().width, this.getSize().height );

        if (template != null){
            synchronized(template){
                template.paint(g2, 1);
            }
        }
        
        // if this item is selected, draw a blue border
        if (selectedItem != null){
            
            // save old stroke and color
            Color oldColor = g2.getColor();
            Stroke oldStroke = g2.getStroke();
            
            // draw the border
            g2.setColor(Color.BLUE);
            g2.setStroke( new BasicStroke(2) );
            g2.drawRect( selectedItem.getLeft(), selectedItem.getTop(), selectedItem.getWidth(), selectedItem.getHeight() );
            
            // restore old settings
            g2.setColor(oldColor);
            g2.setStroke( oldStroke );
            
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped
    
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if ((selectedItem != null) && (dragBegin!=null)){
            
            // moving or resizing?
            if ( (evt.getModifiers() & InputEvent.SHIFT_MASK ) == 0 ){
                selectedItem.setLeft( (int) ( evt.getX() - dragBegin.getX() + dragOrigSize.getX() ) );
                selectedItem.setTop( (int) ( evt.getY() - dragBegin.getY() + dragOrigSize.getY() ) );
            }else{
                
                int newW = (int) ( dragOrigSize.getWidth() + evt.getX() - dragBegin.getX() );
                int newH = (int) ( dragOrigSize.getHeight() + evt.getY() - dragBegin.getY() );
                
                
                if (newW < 5) newW = 5;
                if (newH < 5) newH = 5;
                
                selectedItem.setWidth( newW );
                selectedItem.setHeight( newH );
            }
        }
        
    }//GEN-LAST:event_formMouseDragged
    
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        dragBegin = null;
    }//GEN-LAST:event_formMouseReleased
    
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Point p = evt.getPoint();
        selectedItem = null;
        
        // scan the items array from the end to the beginning to
        // get first the upmost item at the clicked position
        for (int i=template.getItems().size()-1; i>=0; i--){
            TemplateItem t = template.getItems().get(i);
            if (t.containsPoint(p)){
                selectedItem = t;
                propTable.setDefaultEditor(Object.class, t.getTableCellEditor() );
                propTable.setDefaultRenderer(AttributedObject.class, t.getColorTableCellRenderer() );
                propTable.setModel(t);
                ResourceBundle bundle = java.util.ResourceBundle.getBundle("datasoul/internationalize"); // NOI18N
                if (t instanceof TextTemplateItem){
                    activeItemLabel.setText(bundle.getString("Text"));
                }else if (t instanceof ImageTemplateItem){
                    activeItemLabel.setText(bundle.getString("Image"));
                }else if (t instanceof TimerProgressbarTemplateItem){
                    activeItemLabel.setText(bundle.getString("Timer"));
                }else{
                    activeItemLabel.setText("");
                }
                break;
            }
        }
        
        if ((selectedItem != null) && (selectedItem.containsPoint(p))){
            dragBegin = p;
            dragOrigSize = selectedItem.getBoundingRect().getBounds();
        }
        
        if (selectedItem == null && propTable != null){
            unselectItem();
        }
        
        this.repaint();
        
    }//GEN-LAST:event_formMousePressed
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    }//GEN-LAST:event_formMouseClicked
    
    public void setEditorFrame(TemplateEditorFrame prop){
        this.propTable = prop.getPropertiesTable();
        this.activeItemLabel = prop.getActiveItemLabel();
        this.editorFrame = prop;
        // NetBeans IDE does not works well when creating 
        // objects in the constructor. So, we allocate the first template here
        if (template == null){
            template = new DisplayTemplate();
        }
        propTable.setDefaultEditor(Object.class, template.getTableCellEditor() );
        propTable.setDefaultRenderer(AttributedObject.class, template.getColorTableCellRenderer() );
        propTable.setModel(template);
        if (template.getName() != null){
            activeItemLabel.setText(template.getName());
        }else{
            activeItemLabel.setText("New Template");
        }

    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void tableChanged(TableModelEvent e) {
        if (template.getWidth() != this.getWidth() || 
            template.getWidth() != this.getHeight() ){
            Dimension d = new Dimension(template.getWidth(), template.getHeight());
            this.setMaximumSize(d);
            this.setMinimumSize(d);
            this.setPreferredSize(d);
            this.setSize(d);
            this.editorFrame.getRootPane().revalidate();
        }
        this.repaint();
    }
    
    public void unselectItem(){
        selectedItem = null;
        propTable.setDefaultEditor(Object.class, template.getTableCellEditor() );
        propTable.setDefaultRenderer(AttributedObject.class, template.getColorTableCellRenderer() );
        propTable.setModel(template);
        if (template.getName() != null){
            activeItemLabel.setText(template.getName());
        }else{
            activeItemLabel.setText("New Template");
        }
        repaint();
    }
    
    
    public void deleteSelectedItem(){
        if (selectedItem != null){
            selectedItem.removeTableModelListener(this);
            template.removeItem(selectedItem);
            selectedItem = null;
            this.repaint();
        }
    }
    
    public DisplayTemplate getTemplate(){
        return this.template;
    }
    
    public void moveUpSelectedItem(){
        if (selectedItem != null){
            template.moveUp(selectedItem);
            this.repaint();
        }
    }

    public void moveDownSelectedItem(){
        if (selectedItem != null){
            template.moveDown(selectedItem);
            this.repaint();
        }
    }
    
    public void save(){
        
        // TODO: Adicionar Validacao de nome
        
        try{
            template.save(this);
        }catch(Exception e) {
            ShowDialog.showWriteFileError(template.getName(), e);
            e.printStackTrace();
        }        
    }

    public void saveAs(){

        // TODO: Adicionar Validacao de nome

        try{
            template.saveAs(this);
        }catch(Exception e) {
            ShowDialog.showWriteFileError(template.getName(), e);
            e.printStackTrace();
        }
    }


    public void open(String templatename){
        try{
            selectedItem = null;
            
            template = TemplateManager.getInstance().newDisplayTemplate(templatename);
            template.addTableModelListener(this);
           
            for (TemplateItem t : template.getItems()){
                t.addTableModelListener(this);
            }
            
            propTable.setModel(template);
            if (template.getName() != null){
                activeItemLabel.setText(template.getName());
            }else{
                activeItemLabel.setText("New Template");
            }
            this.repaint();
        }catch(Exception e) {
            ShowDialog.showReadFileError(template.getName(), e);
        }        
        
    }
    
    public void openNewTemplate(){
        
        try{
            // Create a new one
            selectedItem = null;
            template = new DisplayTemplate();
            template.addTableModelListener(this);
            propTable.setModel(template);
            if (template.getName() != null){
                activeItemLabel.setText(template.getName());
            }else{
                activeItemLabel.setText("New Template");
            }
            this.repaint();
            
        }catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Unable_to_create_template:")+e.getMessage(),java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Datasoul_Error"),0);
        }
    }

    public void close(){
        template.removeTableModelListener(this);
        for (TemplateItem t : template.getItems()){
            t.removeTableModelListener(this);
        }
        editorFrame = null;
    }
    
}
