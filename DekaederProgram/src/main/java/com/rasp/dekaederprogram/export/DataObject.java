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

    protected DataObject() {
    }

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
        return name.length()*2+6+chars.length();
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
            //If the next Area is a loop, then
            if(nextAreaIsALoop(searchString)){
                return new LoopObject(insideArea(MetaObject.STARTIDENTIFIER, searchString),
                        insideArea(MetaObject.STOPIDENTIFIER, searchString),
                        insideArea("/" + MetaObject.STARTIDENTIFIER, MetaObject.STOPIDENTIFIER, searchString)
                        );
            }else{
                newString = searchString.substring(searchString.indexOf("{" + newName + "}")+newName.length()+2, searchString.indexOf("{/" + newName + "}")).trim();
            }
        }
        //Borde kolla om det inte blir något objekt.
        return new MetaObject(newString, newName);
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

    private static String insideArea(final String identifier, String context){
        return context.substring(context.indexOf("{" + identifier + "}")+identifier.length()+2, context.indexOf("{/" + identifier + "}"));
    }

    private static String insideArea(final String startIdentifier, final String stopIdentifier, String context){
        return context.substring(context.indexOf("{" + startIdentifier + "}")+startIdentifier.length()+2, context.indexOf("{" + stopIdentifier + "}"));
    }

    //TODO!!! Lägg in fler checkar för att det är en loop som kommer härnäst
    /**
     *
     */
    private static boolean nextAreaIsALoop(String searchString) {
        try{
            return getNextAreaName(searchString).equals(LoopObject.STARTIDENTIFIER);
        }catch(IOException e){
            //Borde kanske lägga till mer problem här...
            return false;
        }
    }

//    public static MetaObject createMetaObjectFromFirstTimeOccurrence(String searchString, DataObject tmpObject){
//        int begin = searchString.indexOf(tmpObject.getBeginSearchName())+tmpObject.getBeginSearchName().length();
//        int end = searchString.indexOf(tmpObject.getEndSearchName());
//        String dataName = searchString.substring(begin, end);
//        return new MetaObject(dataName, tmpObject);   //STARTA HÄR NÄSTA GÅNG!
//    }

}
