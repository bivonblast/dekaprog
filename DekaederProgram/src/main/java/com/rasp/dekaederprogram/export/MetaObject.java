package com.rasp.dekaederprogram.export;

import com.rasp.dekaederprogram.character.data.AdvantageTrait;
import com.rasp.dekaederprogram.character.data.HookTrait;
import com.rasp.dekaederprogram.character.data.SkillTrait;
import com.rasp.dekaederprogram.character.data.SkillType;
import com.rasp.dekaederprogram.character.data.Trait;
import com.rasp.dekaederprogram.character.data.TraitHandler;
import java.util.ArrayList;

/**
 *
 * @author MARTIN
 */
public class MetaObject extends DataObject{
    private String otherName;
    private String preText;
    private String postText;
    private String headLine;
    protected ArrayList<MetaObject> allObjects;
    private int value;
    private boolean hasAValue = false;
    private TraitHandler trait;
    public static String STARTSLINGA = "{startSlinga}";
    public static String STARTIDENTIFIER = "startSlinga";
    public static String STARTSLINGAEND = "{/startSlinga}";
    public static String STOPSLINGA = "{slutSlinga}";
    public static String STOPIDENTIFIER = "slutSlinga";
    public static String STOPSLINGAEND = "{/slutSlinga}";

    protected MetaObject(){
    }

    public MetaObject(String chars, String name) {
        super(chars, name);
//        this.otherName = name;
        init();
    }

    public MetaObject(String otherName, String chars, String name) {
        super(chars, name);
        this.otherName = otherName;
        init();
    }

    public MetaObject(String otherName, String chars, String name, int value) {
        super(chars, name);
        this.otherName = otherName;
        this.value = value;
        init();
    }
    
    public MetaObject(String otherName, String chars, String name, String preText, String postText) {
        super(chars, name);
        this.otherName = otherName;
        this.preText = preText;
        this.postText = postText;
        init();
    }

    public MetaObject(String otherName, String chars, String name, String preText, String postText, int value) {
        super(chars, name);
        this.otherName = otherName;
        this.preText = preText;
        this.postText = postText;
        this.value = value;
        this.hasAValue = true;
        init();
    }

    public boolean hasValue(){
        return hasAValue;
    }

    public Trait createTrait(){
        
        switch(TraitType.getTrait(getName())){
            case TraitType.PHYSICAL_SKILL:
                return new SkillTrait(otherName, SkillType.newPhysicalSkillType(), value);
            case TraitType.SOCIAL_SKILL:
                return new SkillTrait(otherName, SkillType.newSocialSkillType(), value);
            case TraitType.MENTAL_SKILL:
                return new SkillTrait(otherName, SkillType.newMentalSkillType(), value);
            case TraitType.ADVANTAGE:
                return new AdvantageTrait(otherName, value);
            case TraitType.HOOK:
                return new HookTrait(otherName, "" + value);
            default:
                return new SkillTrait(otherName, SkillType.newMentalSkillType(), 0);
        }
    }

    private void init() {
//        trait = decideTrait();
        allObjects = new ArrayList<MetaObject>();
        createParser();
    }

    public void printObjects(String parser){
        System.out.println(parser + getName());
        if(!allObjects.isEmpty()){
            for(MetaObject curObject : allObjects){
                curObject.printObjects(parser + " ");
            }
        }
    }

//    private TraitHandler decideTrait(){
//        if(name.equals("Koncept")){
//            return new SkillTrait()
//        }else if(name.equals("Färdigheter")){
//        }else if(name.equals("Fördelar")){
//        }else if(name.equals("Krokar")){
//        }else if(name.equals("Poäng")){
//        }else if(name.equals("Övrigt")){
//        }
//    }

    private void createParser() {
        String tmpChars = chars;
        String tmpPart;
        headLine = tmpChars.substring(0, chars.indexOf("{")).trim();
        tmpChars = tmpChars.substring(tmpChars.indexOf("{"));
        while(!tmpChars.equals("")){
            tmpChars = tmpChars.trim();
            if(tmpChars.indexOf(STARTSLINGA) >= 0){
                String startSlinga = tmpChars.substring(tmpChars.indexOf(STARTSLINGA)+STARTSLINGA.length(), tmpChars.indexOf(STARTSLINGAEND));
                String stopSlinga = tmpChars.substring(tmpChars.indexOf(STOPSLINGA)+STOPSLINGA.length(), tmpChars.indexOf(STOPSLINGAEND));
                String insideLoop = tmpChars.substring(tmpChars.indexOf(STARTSLINGAEND)+STARTSLINGAEND.length(), tmpChars.indexOf(STOPSLINGA));
                LoopObject addSlinga = new LoopObject(startSlinga, stopSlinga, insideLoop);
                tmpChars = tmpChars.substring(tmpChars.indexOf(STOPSLINGAEND) + STOPSLINGAEND.length());
                allObjects.add(addSlinga);

            }else{
                MetaObject newObject = createNextMetaObject(tmpChars);
                allObjects.add(newObject);
                tmpChars = tmpChars.substring(newObject.getFullLength()).trim();
            }

            tmpChars = tmpChars.trim();
        }
        //Can't remember why I did this... ???
//        tmpChars = tmpChars.trim();
//        tmpChars = tmpChars.substring(getBeginSearchNameLength());
//        tmpChars = tmpChars.trim();
        //tmpPart = tmpChars.substring(type)
    }
}
