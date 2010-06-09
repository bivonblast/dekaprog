/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import java.io.IOException;

/**
 *
 * @author MARTIN
 */
public class DataObject extends Object{
    protected String chars;
    protected String name;
    protected int type;
    private static int i = 0;
    public final static int TYPE_BEGIN_LOOP = ++i; //1
    public final static int TYPE_END_LOOP = ++i;
    public final static int TYPE_ABILITY = ++i;

    public DataObject(String chars, String name) {
        this.chars = chars;
        this.name = name;
    }

    public DataObject(String allValues) {
        int begin = allValues.indexOf("{")+1;
        int end = allValues.indexOf("}");
        this.name = allValues.substring(begin, end);
        this.chars = allValues.substring(getBeginSearchNameLength(), chars.length()-getEndSearchNameLength()+1);
    }

    public String getBeginSearchName(){
        return "{" + name + "}";
    }

    public int getBeginSearchNameLength(){
        return name.length()+2;
    }

    public String getEndSearchName(){
        return "{/" + name + "}";
    }

    public int getEndSearchNameLength(){
        return name.length()+3;
    }

    public String getName() {
        return name;
    }

    public int getFullLength() {
        return name.length()*2+5+chars.length();
    }


    public static MetaObject createNextMetaObject(final String searchString){
        String newName = "";
        String newString = "";
        if(hasArea(searchString)){
            try{
                newName = getNextAreaName(searchString);
            }catch(IOException e){
                System.err.println(e);
                System.out.println(searchString);
            }
            newString = searchString.substring(searchString.indexOf("{" + newName + "}")+newName.length()+2, searchString.indexOf("{/" + newName + "}"));
        }
        return new MetaObject(newName, newString);
    }


    public static boolean hasArea(String articleString) {
        if(articleString.contains("{") && articleString.contains("}") && articleString.contains("{/") && articleString.lastIndexOf("}") != articleString.indexOf("}")){
            return true;
        }else{
            return false;
        }
    }

    private static String getNextAreaName(final String articleString) throws IOException{
        int begin = articleString.indexOf("{")+1;
        int end = articleString.indexOf("}");
        if(articleString.contains("{/" + articleString.substring(begin, end) + "}")){
            return articleString.substring(begin, end);
        }else{
            throw new IOException("no end of this Area-type.");
        }
    }


//    public static MetaObject createMetaObjectFromFirstTimeOccurrence(String searchString, DataObject tmpObject){
//        int begin = searchString.indexOf(tmpObject.getBeginSearchName())+tmpObject.getBeginSearchName().length();
//        int end = searchString.indexOf(tmpObject.getEndSearchName());
//        String dataName = searchString.substring(begin, end);
//        return new MetaObject(dataName, tmpObject);   //STARTA HÄR NÄSTA GÅNG!
//    }

}
