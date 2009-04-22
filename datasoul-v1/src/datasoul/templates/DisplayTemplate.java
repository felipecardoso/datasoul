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
 * DisplayTemplate.java
 *
 * Created on December 31, 2005, 12:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package datasoul.templates;

import datasoul.DatasoulMainForm;
import datasoul.util.AttributedObject;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JComboBox;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.xml.serialize.OutputFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author samuelm
 */
public class DisplayTemplate extends AttributedObject {
    
    public final static int TEMPLATE_WIDTH = 800;
    public final static int TEMPLATE_HEIGHT = 600;
    
    ArrayList<TemplateItem> items;
    
    static int defaultItemNameCount = 1;
    
    static TreeMap<String, DisplayTemplate> templateCache = new TreeMap<String, DisplayTemplate>();
    
    private String name;
    
    private int transitionKeepBG;
    public static final int KEEP_BG_YES = 0;
    public static final int KEEP_BG_NO = 1;
    public static final String[] KEEP_BG_TABLE = {java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Yes"), java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("No")};
    private static JComboBox cbKeepBG;
    
    /** Creates an empty DisplayTemplate */
    public DisplayTemplate() {
        super();
        items = new ArrayList<TemplateItem>();
        
        this.setTransitionKeepBGIdx(KEEP_BG_YES);
        
        if (cbKeepBG == null){
            cbKeepBG = new JComboBox();
            for (int i=0; i<KEEP_BG_TABLE.length; i++)
                cbKeepBG.addItem(KEEP_BG_TABLE[i]);
        }
        registerEditorComboBox("TransitionKeepBGIdx", cbKeepBG);
    }

    /**
     * Loads an existing DisplayTemplate
     */
    public DisplayTemplate(String name) throws Exception  {
        this();
        
        if (templateCache.containsKey(name)){
            this.assign(templateCache.get(name));
        }else{
            this.loadFromFile(name);
            templateCache.put(name, this);
        }
        
    }
    
    
    public static String findUnusedName(){
        
        // try to find a Name
        String path = System.getProperty("datasoul.stgloc") + System.getProperty("file.separator") + "templates";
        int i = 0;
        File file = new File(path);
        String[] files = file.list();
        
        while (true){
            
            boolean exists = false;
            String tmp = java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Untitled")+i;
            for (String s : files){
                if (s.equals(tmp+".template")){
                    exists = true;
                    break; //for
                }
            }
            
            if (exists == false){
                return tmp;
            }else{
                i++;
            }

        }
        
    }
    
    
    public void assign(DisplayTemplate from){
        
        this.setName(from.getName());
        this.setTransitionKeepBG(from.getTransitionKeepBG());
        this.items.clear();
        for (TemplateItem t : from.getItems()){
            
            Class<?> itemClass = t.getClass();
            try {
                TemplateItem newItem = (TemplateItem) itemClass.newInstance();
                Class[] formalParams = { itemClass  };
                Object[] actualParams = { t };
                itemClass.getMethod("assign", formalParams).invoke(newItem, actualParams);
                this.items.add(newItem);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        
    }
    
    private void loadFromFile(String name) throws Exception{
        
        String path = System.getProperty("datasoul.stgloc") + System.getProperty("file.separator") + "templates";
        String filename = path + System.getProperty("file.separator") + name + ".template";

        Document dom=null;
        Node node=null;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        //Using factory get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();

        // Open the file
        FileInputStream fis = new FileInputStream(filename);
        
        GZIPInputStream zis = null;
        try{
            zis = new GZIPInputStream(fis);
        }catch(IOException e){
            zis = null;
        }
        
        //parse using builder to get DOM representation of the XML file
        if (zis != null){
            // file in GZIP format
            dom = db.parse(zis);
        }else{
            // uncompressed file
            dom = db.parse(filename);
        }
        
        //node = dom.getDocumentElement().getChildNodes().item(0);
        node = dom.getElementsByTagName("DisplayTemplate").item(0);

        // Close the input stream
        fis.close();
        
        this.readObject(node);
        
        // upgrade from older versions
        
        // version up to 1.2 had fixed resolution at 640x480
        if (getDatasoulFileVersion() < 1){
            setResolution(640, 480);
        }
        setDatasoulFileVersion( DatasoulMainForm.getVersion() );
    }
    
    private void setResolution(int fromWidth, int fromHeight){
        float fWidth = (float) TEMPLATE_WIDTH / (float) fromWidth;
        float fHeight = (float) TEMPLATE_HEIGHT / (float) fromHeight;
        for (TemplateItem it : items){
            it.setWidth((int) (fWidth * it.getWidth()));
            it.setHeight((int)(fHeight * it.getHeight()));
            it.setTop((int) (fWidth * it.getTop()));
            it.setLeft((int)(fHeight * it.getLeft()));
        }
    }
    
    @Override
    protected void registerProperties(){
        super.registerProperties();
        properties.add("Name");
        registerDisplayString("Name", java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Template_Name"));
        properties.add("TransitionKeepBGIdx");
        registerDisplayString("TransitionKeepBGIdx", java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Transition_Keep_Background"));
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        
        // TODO: Adicionar Validacao de nome aqui
        
        this.name = name;
        firePropChanged("Name");
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    
    /**
     * items are exported as default access level to
     * allow other classes in this package access it directly.
     * This is needed by the TempalteEditorPanel.
     * But the general rule is that other components shall
     * avoid using it directly.
     */
    public ArrayList<TemplateItem> getItems(){
        return items;
    }
    
    public void addItem(TemplateItem t){
        if (t.getName().equals("")){
            t.setName(java.util.ResourceBundle.getBundle("datasoul/internationalize").getString("Item_#")+defaultItemNameCount++);
        }
        
        items.add(t);
    }
    
    public void removeItem(TemplateItem t){
        items.remove(t);
    }
    
    /**
     * Paint the template in the given Graphics2D g.
     * The 'time' parameter is used for transiction effects. The value range
     * is 0 to 1, where 0 is hidden and 1 is shown.
     * 
     * @param g The graphic to paint to
     * @param time valid values are between 0 (hidden) and 1 (shown). 
     */
    public synchronized void paint(Graphics2D g, float time){
        
        for (TemplateItem t : items){
            t.draw( g, time );
        }
        
    }
    
    public void moveUp(TemplateItem t){
        int size = items.size();
        for (int i=0; i<size-1; i++){
            if (items.get(i) == t){
                TemplateItem other = items.get(i+1);
                items.set(i+1, t);
                items.set(i, other);
                break;
            }
        }
    }
    
    public void moveDown(TemplateItem t){
        int size = items.size();
        for (int i=1; i<size; i++){
            if (items.get(i) == t){
                TemplateItem other = items.get(i-1);
                items.set(i-1, t);
                items.set(i, other);
                break;
            }
        }
    }
    
    @Override
            public Node writeObject() throws Exception{
        
        Node n = super.writeObject();
        
        Node nodeItems = n.getOwnerDocument().createElement("TemplateItems");
        
        for (int i=0; i< items.size(); i++){
            
            Node ti =  items.get(i).writeObject();
            
            nodeItems.appendChild( nodeItems.getOwnerDocument().importNode(ti, true) );
            
        }
        
        n.appendChild(nodeItems);
        
        return n;
        
    }
    
    @Override
            public void readObject(Node nodeIn) {
        
        // read the properties
        super.readObject(nodeIn);
        
        // now, read the template Items
        NodeList nodeList= nodeIn.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++){
            // find the templateItem tag in document
            if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE &&
                    nodeList.item(i).getNodeName().equals("TemplateItems") ){
                
                // for each templateItem, create it and add it to the list
                NodeList templateItemsNodes = nodeList.item(i).getChildNodes();
                for (int j=0; j<templateItemsNodes.getLength(); j++){
                    if ( templateItemsNodes.item(j).getNodeType() == Node.ELEMENT_NODE ){
                        
                        // Determine the type for the TemplateItem
                        String className = templateItemsNodes.item(j).getNodeName();
                        className = this.getClass().getPackage().getName() + "." + className;
                        Class cl;
                        Object ti = null;
                        
                        try {
                            cl = Class.forName(className);
                            ti = cl.newInstance();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                        // set the properties
                        if (ti instanceof TemplateItem){
                            ((TemplateItem) ti).readObject( templateItemsNodes.item(j) );
                            this.addItem( (TemplateItem) ti );
                        }
                    }
                }
                
            }
        }
        
        
    }
    
    public void setTemplateItems(String s){
        // just to conformance with super's readObject.
        // The TemplateItems will be readed lately by the overriden readObject
    }
    
    
    public void save() throws Exception {
        
        String path = System.getProperty("datasoul.stgloc") + System.getProperty("file.separator") + "templates";
        String filename = path + System.getProperty("file.separator") + this.getName()+".template";
        
        Node node = this.writeObject();
        Document doc = node.getOwnerDocument();
        doc.appendChild( node);                        // Add Root to Document
        
        FileOutputStream fos = new FileOutputStream( filename );
        GZIPOutputStream zos = new GZIPOutputStream(fos);
        
        org.apache.xml.serialize.XMLSerializer xs = new org.apache.xml.serialize.XMLSerializer();
        OutputFormat outFormat = new OutputFormat();
        outFormat.setIndenting(true);
        outFormat.setEncoding("ISO-8859-1");
        xs.setOutputFormat(outFormat);
        xs.setOutputByteStream(zos);
        xs.serialize(doc);
        
        zos.close();
        
        templateCache.put(name, this);
    }
    

    public void cleanUp(){
        // clean up
        for (TemplateItem t : items ){
            if (t instanceof TextTemplateItem) {
                if (! ((TextTemplateItem)t).getContent().equals(TextTemplateItem.CONTENT_STATIC) ){
                    ((TextTemplateItem)t).setText(TextTemplateItem.DEFAULT_TEXT);
                }
            }                
        }
        
    }
    
    public static void deleteTemplate(String name){
        templateCache.remove(name);
    }

    public void setTransitionKeepBGIdx(int i){
        this.transitionKeepBG = i;
        firePropChanged("TransitionKeepBGIdx");
    }

    public void setTransitionKeepBGIdx(String i){
        setTransitionKeepBGIdx(Integer.parseInt(i));
    }
    
    public void setTransitionKeepBG(String str){
        for (int i=0; i<KEEP_BG_TABLE.length; i++){
            if (str.equalsIgnoreCase(KEEP_BG_TABLE[i])){
                setTransitionKeepBGIdx(i);
            }
        }
    }
    
    
    public int getTransitionKeepBGIdx(){
        return transitionKeepBG;
    }

    public String getTransitionKeepBG(){
        return KEEP_BG_TABLE[transitionKeepBG];
    }
    
}
