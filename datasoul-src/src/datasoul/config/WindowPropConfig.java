/* 
 * Copyright 2005-2010 Samuel Mello
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; version 2 or later of the License.
 * 
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 * 
 */

package datasoul.config;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 *
 * @author samuel
 */
public class WindowPropConfig extends AbstractConfig {


    private static WindowPropConfig instance = null;

    public static synchronized WindowPropConfig getInstance(){
        if (instance == null){
            instance = new WindowPropConfig();
        }
        return instance;
    }

    /** Creates a new instance of ConfigObj */
    private WindowPropConfig() {
        load("windowProp.config");
    }

    public void save(){
        save("windowProp.config");
    }

    private String hMainForm;
    private String wMainForm;
    private String hTextEditor;
    private String wTextEditor;
    private String hSongEditor;
    private String wSongEditor;
    private String hTemplateEditor;
    private String wTemplateEditor;
    private String templateSplit1;
    private String splMain;
    private String splService;
    private String splSongLibrary;
    private String splDisplayControl;
    private String selectedBible;
    private boolean showBibleControls;
    private String textSplitLine;
    private String textSplitSlide;

    protected void registerProperties() {
        super.registerProperties();
        properties.add("HMainForm");
        properties.add("WMainForm");
        properties.add("HTextEditor");
        properties.add("WTextEditor");
        properties.add("HSongEditor");
        properties.add("WSongEditor");
        properties.add("HTemplateEditor");
        properties.add("WTemplateEditor");
        properties.add("TemplateSplit1");
        properties.add("SplMain");
        properties.add("SplService");
        properties.add("SplSongLibrary");
        properties.add("SplDisplayControl");
        properties.add("SelectedBible");
        properties.add("ShowBibleControls");
        properties.add("TextSplitLine");
        properties.add("TextSplitSlide");
   }

    private boolean checkStr(String str){
        return !(str == null || str.length() == 0);
    }

    public String getHMainForm() {
        return hMainForm;
    }

    public void setHMainForm(String hMainForm) {
        this.hMainForm = hMainForm;
    }

    public String getWMainForm() {
        return wMainForm;
    }

    public void setWMainForm(String wMainForm) {
        this.wMainForm = wMainForm;
    }

    public void setMainForm(JFrame m){
        setWMainForm(Integer.toString(m.getWidth()));
        setHMainForm(Integer.toString(m.getHeight()));
        save();
    }

    public void getMainForm(JFrame m){
        if (checkStr(wMainForm) && checkStr(hMainForm)){
            try{
                m.setSize(Integer.parseInt(wMainForm), Integer.parseInt(hMainForm));
            }catch(Exception e){
                //ignore
            }
        }
    }

    public String getHTextEditor() {
        return hTextEditor;
    }

    public void setHTextEditor(String hTextEditor) {
        this.hTextEditor = hTextEditor;
    }

    public String getWTextEditor() {
        return wTextEditor;
    }

    public void setWTextEditor(String wTextEditor) {
        this.wTextEditor = wTextEditor;
    }


    public void setTextEditor(JFrame m){
        setWTextEditor(Integer.toString(m.getWidth()));
        setHTextEditor(Integer.toString(m.getHeight()));
        save();
    }

    public void getTextEditor(JFrame m){
        if (checkStr(wTextEditor) && checkStr(hTextEditor)){
            try{
                m.setSize(Integer.parseInt(wTextEditor), Integer.parseInt(hTextEditor));
            }catch(Exception e){
                //ignore
            }
        }
    }


    public String getHSongEditor() {
        return hSongEditor;
    }

    public void setHSongEditor(String hSongEditor) {
        this.hSongEditor = hSongEditor;
    }

    public String getWSongEditor() {
        return wSongEditor;
    }

    public void setWSongEditor(String wSongEditor) {
        this.wSongEditor = wSongEditor;
    }


    public void setSongEditor(JFrame m){
        setWSongEditor(Integer.toString(m.getWidth()));
        setHSongEditor(Integer.toString(m.getHeight()));
        save();
    }

    public void getSongEditor(JFrame m){
        if (checkStr(wSongEditor) && checkStr(hSongEditor)){
            try{
                m.setSize(Integer.parseInt(wSongEditor), Integer.parseInt(hSongEditor));
            }catch(Exception e){
                //ignore
            }
        }
    }

    public String getWTemplateEditor() {
        return wTemplateEditor;
    }

    public void setWTemplateEditor(String wTemplateEditor) {
        this.wTemplateEditor = wTemplateEditor;
    }

    public String getHTemplateEditor() {
        return hTemplateEditor;
    }

    public void setHTemplateEditor(String hTemplateEditor) {
        this.hTemplateEditor = hTemplateEditor;
    }


    public void setTemplateEditor(JFrame m){
        setWTemplateEditor(Integer.toString(m.getWidth()));
        setHTemplateEditor(Integer.toString(m.getHeight()));
        save();
    }

    public void getTemplateEditor(JFrame m){
        if (checkStr(wTemplateEditor) && checkStr(hTemplateEditor)){
            try{
                m.setSize(Integer.parseInt(wTemplateEditor), Integer.parseInt(hTemplateEditor));
            }catch(Exception e){
                //ignore
            }
        }
    }

    public void setHSongSearch(String hSongSearch) {
        //backward compatibility
    }

    public void setWSongSearch(String wSongSearch) {
        //backward compatibility
    }

    public void setDatashowSplit1(String datashowSplit1) {
        //backward compatibility
    }

    public void setDatashowSplit2(String datashowSplit2) {
        //backward compatibility
    }

    public void setDatashowSplit3(String datashowSplit3) {
        //backward compatibility
    }

    public void setServiceSplit1(String serviceSplit1) {
        //backward compatibility
    }

    public void setServiceSplit2(String serviceSplit2) {
        //backward compatibility
    }

    public void setServiceSplit3(String serviceSplit3) {
        //backward compatibility
    }

    public void setSongSplit1(String songSplit1) {
        //backward compatibility
    }

    public void setSongSplit2(String songSplit2) {
        //backward compatibility
    }

    public void setTemplateSplit2(String templateSplit2) {
        //backward compatibility
    }

    public String getTemplateSplit1() {
        return templateSplit1;
    }

    public void getTemplateSplit1(JSplitPane j){
        if (!checkStr(templateSplit1)) return;
        try{
            j.setDividerLocation(Integer.parseInt(templateSplit1));
        }catch(Exception e){
        }
    }

    public void setTemplateSplit1(String templateSplit1) {
        this.templateSplit1 = templateSplit1;
        save();
    }

    public String getSplMain() {
        return splMain;
    }

    public void getSplMain(JSplitPane j){
        if (!checkStr(splMain)) return;
        try{
            j.setDividerLocation(Integer.parseInt(splMain));
        }catch(Exception e){
        }
    }

    public void setSplMain(String str) {
        this.splMain = str;
        save();
    }

    public String getSplService() {
        return splService;
    }

    public void getSplService(JSplitPane j){
        if (!checkStr(splService)) return;
        try{
            j.setDividerLocation(Integer.parseInt(splService));
        }catch(Exception e){
        }
    }

    public void setSplService(String str) {
        this.splService = str;
        save();
    }

    public String getSplSongLibrary() {
        return splSongLibrary;
    }

    public void getSplSongLibrary(JSplitPane j){
        if (!checkStr(splSongLibrary)) return;
        try{
            j.setDividerLocation(Integer.parseInt(splSongLibrary));
        }catch(Exception e){
        }
    }

    public void setSplSongLibrary(String str) {
        this.splSongLibrary = str;
        save();
    }

    public String getSplDisplayControl() {
        return splDisplayControl;
    }

    public void getSplDisplayControl(JSplitPane j){
        if (!checkStr(splDisplayControl)) return;
        try{
            j.setDividerLocation(Integer.parseInt(splDisplayControl));
        }catch(Exception e){
        }
    }

    public void setSplDisplayControl(String str) {
        this.splDisplayControl = str;
        save();
    }

    public String getSelectedBible(){
        return selectedBible;
    }

    public void setSelectedBible(String s){
        this.selectedBible = s;
        save();
    }

    public boolean getShowBibleControlsBool(){
        return showBibleControls;
    }
    
    public void setShowBibleControlsBool(boolean b){
        if (b){
            setShowBibleControls("true");
        }else{
            setShowBibleControls("false");
        }
    }

    public String getShowBibleControls(){
        if (showBibleControls){
            return "true";
        }else{
            return "false";
        }
    }

    public void setShowBibleControls(String s){
        if (s.equals("true")){
            this.showBibleControls = true;
        }else{
            this.showBibleControls = false;
        }
        save();
    }

    public String getTextSplitLine() {
        if (textSplitLine == null)
            return "";
        else
            return textSplitLine;
    }

    public void setTextSplitLine(String textSplitLine) {
        this.textSplitLine = textSplitLine;
        save();
    }

    public String getTextSplitSlide() {
        if (textSplitSlide == null)
            return "";
        else
            return textSplitSlide;
    }

    public void setTextSplitSlide(String textSplitSlide) {
        this.textSplitSlide = textSplitSlide;
        save();
    }
}

