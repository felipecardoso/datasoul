/*
 * ServiceListTable.java
 *
 * Created on 9 de Janeiro de 2006, 22:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package datasoul.datashow;

import datasoul.*;
import datasoul.util.*;
import datasoul.datashow.*;
import datasoul.song.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Administrador
 */
public class ServiceListTable extends ListTable{
    
    static ServiceListTable instance;
    /** Creates a new instance of ServiceListTable */
    private ServiceListTable() {
    }
    
    static ServiceListTable getInstance(){
        if(instance==null){
            instance = new ServiceListTable();
        }
        return instance;
    }

     public Node writeObject() throws Exception{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        //Using factory get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
         
        Document doc= db.newDocument();
        
        Node nodeOut = doc.createElement("ServiceListTable");
        Node node; 
        String paramName;
        String paramValue;
        for(int i=0;i<objectList.size();i++){
            node = ((SerializableItf)objectList.get(i)).writeObject();
            nodeOut.appendChild(doc.importNode(node,true));
        }
              
        return nodeOut.cloneNode(true);
     }
     
     public void readObject(Node nodeIn) throws Exception{
        this.objectList.clear();
         
        NodeList nodeList= nodeIn.getChildNodes();
        String paramName;
        String paramValue;
        for(int i=0;i<nodeList.getLength();i++){
            if(nodeList.item(i).getNodeType()==1){
                if(nodeList.item(i).getNodeName().equals("Song")){
                    Song song = new Song();
                    song.readObject(nodeList.item(i));
                    this.addItem(song);
                }
            }
        }
     }
    
}
