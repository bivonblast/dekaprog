/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.data.ConceptHandler;
import com.rasp.dekaederprogram.character.data.ConceptTrait;
import com.rasp.dekaederprogram.character.data.SkillHandler;
import com.rasp.dekaederprogram.character.data.SkillTrait;
import com.rasp.dekaederprogram.character.data.SkillType;
import com.rasp.dekaederprogram.character.data.Trait;
import com.rasp.dekaederprogram.character.data.TraitHandler;
import java.util.ArrayList;

/**
 *
 * @author Martin Andersson
 */
class LoopObject<H extends TraitHandler> extends MetaObject{
    public static String STARTIDENTIFIER = "startSlinga";
    public static String STOPIDENTIFIER = "stopSlinga";
    private String startLoop;
    private String stopLoop;
    private String insideLoop;
    private ArrayList<Parser> objectsInLoop;
    
    public LoopObject(String startLoop, String stopLoop, String insideLoop) {
        super(insideLoop, "Loop");
        this.startLoop = startLoop;
        this.stopLoop = stopLoop;
        this.insideLoop = insideLoop;
        objectsInLoop = new ArrayList<Parser>();
        init();
    }

    private void init() {
        //Get all Objects inside loop
        String tmpLoop = insideLoop;
        String prevParser, nextValue;
        while(!insideLoop.trim().equals("")){
            if(tmpLoop.contains("{") && tmpLoop.contains("}")){
                prevParser = tmpLoop.substring(0, tmpLoop.indexOf("{"));
                nextValue = tmpLoop.substring(tmpLoop.indexOf("{")+1, tmpLoop.indexOf("}"));
                tmpLoop = tmpLoop.substring(tmpLoop.indexOf("}")+1);
                objectsInLoop.add(new Parser(prevParser, true));
                objectsInLoop.add(new Parser(nextValue, false));
            }else{
                objectsInLoop.add(new Parser(tmpLoop, true));
                tmpLoop = "";
            }
        }
    }

    public String getStartLoop() {
        return startLoop;
    }

    public void setStartLoop(String startLoop) {
        this.startLoop = startLoop;
    }

    public String getStopLoop() {
        return stopLoop;
    }

    public void setStopLoop(String stopLoop) {
        this.stopLoop = stopLoop;
    }


    private class Parser extends Object{
        private String parser;
        private boolean parserBool;

        protected Parser(String parser, boolean parserBool){
            this.parser = parser;
            this.parserBool = parserBool;
        }

        public boolean isParserBool() {
            return parserBool;
        }

        public void setIsParserBool(boolean parserBool) {
            this.parserBool = parserBool;
        }

        public String getParser() {
            return parser;
        }
        
        public int getLength() {
            return parser.length();
        }

        public String getParserName(){
            if(parserBool){
                return "{" + parser + "}";
            }else{
                return parser;
            }
        }

        public void setParser(String parser) {
            this.parser = parser;
        }

    }

    public void getNextLoopObjects(String loopString, H handler){
        String readString = loopString;
        String nextName = "";
        String nextValue = "";
        String parserName = "";
        String tmpName = "";
        //Searches for all Traits in the loop
        while(!readString.trim().equals("")){
            //Goes through every object found in the loop ones for each time in the while-loop
            for(Parser thisParser: objectsInLoop){
                if(thisParser.isParserBool()){
                    tmpName = readString.substring(0, readString.indexOf(thisParser.getParserName())).trim();
                    if(thisParser.getParser().endsWith("V") && nextValue.equals("")){
                        nextValue = tmpName;
                        parserName = thisParser.getParser();
                    }else if(thisParser.getParser().endsWith("F") && nextValue.equals("")){
                        nextName = tmpName;
                    }
                    readString = readString.substring(readString.indexOf(thisParser.getParserName()+thisParser.getLength()+2));
                }else{
                    readString = readString.substring(readString.indexOf(thisParser.getParser()+thisParser.getLength()));
                }
                //If both a name and a value is found then convert it to a Trait and reset name and value
                if(!nextName.equals("") && !nextValue.equals("")){
                    if(parserName.equals("fysiskF") || parserName.equals("socialF") || parserName.equals("mentalF")){   //Add mental Value
                        if(handler instanceof SkillHandler){
                            int[] values = getSkillValue(nextValue);
                            ((SkillHandler)handler).addSkillTrait(new SkillTrait(nextName, getSkillType(parserName), values[0], values[1]));
                        }
                    }else if(parserName.equals("konceptF")){        //Add concept Value
                        if(handler instanceof ConceptHandler){
                            ((ConceptHandler)handler).add(new ConceptTrait(nextName, nextValue));
                        }
                    }
                    nextValue = "";
                    nextName = "";
                }
            }
        }
        //return tmpSkillHandler;
    }

    public int[] getSkillValue(String value){
        int[] values = new int[2];
        if(value.contains("+")){
            values[0] = Integer.getInteger(value.trim().substring(0, value.trim().indexOf("+"))).intValue();
            values[1] = Integer.getInteger(value.trim().substring(value.trim().indexOf("+")+1)).intValue();
        }else{
            values[0] = Integer.getInteger(value.trim()).intValue();
            values[1] = 0;
        }
        return values;
    }

    public Trait getTrait(String name, String value, String parserName, Trait dummyObject) throws ClassNotFoundException{
        return new Trait(name, value){
            @Override
            public final String valueToString() {
                return getValue().toString();
            }

        };
    }

    private SkillType getSkillType(String parserName) {
        if(parserName.equalsIgnoreCase("fysisk")){
            return SkillType.newPhysicalSkillType();
        }else if(parserName.equalsIgnoreCase("mental")){
            return SkillType.newMentalSkillType();
        }else if(parserName.equalsIgnoreCase("social")){
            return SkillType.newSocialSkillType();
        }else{
            return SkillType.newPhysicalSkillType();
        }
    }

}
